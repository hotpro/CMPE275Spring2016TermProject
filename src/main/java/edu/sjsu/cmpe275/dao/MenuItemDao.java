package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yutao on 5/5/16.
 */
public interface MenuItemDao extends CrudRepository<MenuItem, Long> {
}
