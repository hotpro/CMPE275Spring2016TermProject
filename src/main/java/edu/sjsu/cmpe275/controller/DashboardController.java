package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dao.MenuItemDao;
import edu.sjsu.cmpe275.dao.OrderDao;
import edu.sjsu.cmpe275.dao.OrderItemDao;
import edu.sjsu.cmpe275.domain.MenuItem;
import edu.sjsu.cmpe275.domain.Order;
import edu.sjsu.cmpe275.domain.OrderItem;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private static final String UPLOAD_IMAGES_PATH = "/tmp/cmpe275/upload/upload_images";
	
	@Autowired
    private MenuItemDao menuItemDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;
	
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
		Iterable<MenuItem> list = this.menuItemDao.findByDeletedIs(false);
    	return list;
    }

	@RequestMapping("/removeItem/{id}")
	@ResponseBody
    public Object removeItem(@PathVariable Long id) {
		MenuItem mi = this.menuItemDao.findOne(id);
		mi.setDeleted(true);
		this.menuItemDao.save(mi);
		return listItems();
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

	@RequestMapping(value = "/resetOrder", method = RequestMethod.POST)
	@ResponseBody
	public OrderController.BaseResultTO resetOrder() {
		orderItemDao.deleteAll();
        orderDao.deleteAll();
		return new OrderController.BaseResultTO(0, "Reset success");
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

    @RequestMapping(value = "/getSystemReport", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderHistory> getOrderHistory(@RequestParam long startTime, @RequestParam long endTime) {
        List<OrderHistory> res = new ArrayList<>();
        List<Order> orderList = orderDao.findByOrderTimeBetween(new Date(startTime), new Date(endTime));
        for (Order order : orderList) {
            List<ItemAndCount> itemAndCountList = new ArrayList<>();
            for (OrderItem orderItem : order.getItemList()) {
                ItemAndCount itemAndCount = new ItemAndCount(orderItem.getItem().getName(), orderItem.getCount());
                itemAndCountList.add(itemAndCount);
            }
            int status = 0;
            long now = Instant.now().toEpochMilli();
            long start = order.getStartPrepareTime().getTime();
            long finish = order.getFinishTime().getTime();
            if (now < start) {
                status = 0;
            } else if (now >= start && now <= finish) {
                status = 1;
            } else {
                status = 2;
            }
            OrderHistory orderHistory = new OrderHistory(order.getId(), itemAndCountList, order.getTotalPrice(),
                    order.getOrderTime(), order.getStartPrepareTime(), order.getFinishTime(),
                    order.getPickUpTime(), order.getUser().getEmail(), status);
            res.add(orderHistory);
        }

        return res;
    }

	static class ItemAndCount {
		String itemName;
		int count;

		public ItemAndCount(String itemName, int count) {
			this.itemName = itemName;
			this.count = count;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

	static class OrderHistory {
		long orderId;

		List<ItemAndCount> itemAndCount;
		double totalPrice;

        Date orderTime;
        Date startTime;
        Date readyTime;
		Date pickupTime;

        String customerEmail;

		/**
		 * 0 not start, 1 processing,  2 done
		 */
		int status;

        public OrderHistory(long orderId, List<ItemAndCount> itemAndCount, double totalPrice,
                            Date orderTime, Date startTime, Date readyTime, Date pickupTime, String customerEmail, int status) {
            this.orderId = orderId;
            this.itemAndCount = itemAndCount;
            this.totalPrice = totalPrice;
            this.orderTime = orderTime;
            this.startTime = startTime;
            this.readyTime = readyTime;
            this.pickupTime = pickupTime;
            this.customerEmail = customerEmail;
            this.status = status;
        }

        public long getOrderId() {
			return orderId;
		}

		public void setOrderId(long orderId) {
			this.orderId = orderId;
		}

		public List<ItemAndCount> getItemAndCount() {
			return itemAndCount;
		}

		public void setItemAndCount(List<ItemAndCount> itemAndCount) {
			this.itemAndCount = itemAndCount;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public Date getPickupTime() {
			return pickupTime;
		}

		public void setPickupTime(Date pickupTime) {
			this.pickupTime = pickupTime;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

        public Date getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Date orderTime) {
            this.orderTime = orderTime;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getReadyTime() {
            return readyTime;
        }

        public void setReadyTime(Date readyTime) {
            this.readyTime = readyTime;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }
    }
}
