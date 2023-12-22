package com.momo.dao;

import com.momo.entity.User;
import com.momo.mapper.SuperMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends SuperMapper<User> {
}
