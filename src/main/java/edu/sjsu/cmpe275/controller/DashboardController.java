package edu.sjsu.cmpe275.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.domain.MenuItem;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private static final String UPLOAD_IMAGES_PATH = "/Users/xiaofengli/documents/upload/upload_images";
	
	@Autowired
    private MenuItemDao menuItemDao;
	
	public void setMenuItemDao(MenuItemDao menuItemDao) {
		this.menuItemDao = menuItemDao;
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	@ResponseBody
    public Object addItem(@RequestBody MenuItem item) {
    	if (item != null) {
    		this.menuItemDao.save(item);
    		return item;
    	}
    	return null;
    }
	
	@RequestMapping("/listItems")
	@ResponseBody
    public Object listItems() {
		Iterable<MenuItem> list = this.menuItemDao.findAll();
    	return list;
    }

	@RequestMapping("/removeItem/{id}")
	@ResponseBody
    public Object removeItem(@PathVariable Long id) {
		this.menuItemDao.delete(id);
		return null;
    }

	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	@ResponseBody
    public Object addImage(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        if (file == null) {
        	return "";
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        ImageVO image = null;
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
        	byte[] thedigest = md.digest(file.getBytes());
			String fileName = MD5Encoder.encode(thedigest) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File targetFile = new File(UPLOAD_IMAGES_PATH, fileName);
	        file.transferTo(targetFile);
	        image = new ImageVO(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return image;
    }
	
	public static class ImageVO {
		private String url;
		
		public ImageVO() {
			
		}

		public ImageVO(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
	}
}
