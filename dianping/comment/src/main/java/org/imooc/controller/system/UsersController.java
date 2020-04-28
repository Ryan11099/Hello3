package org.imooc.controller.system;

import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.PageCodeDto;
import org.imooc.dto.UserDto;
import org.imooc.service.UserService;
import org.imooc.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.imooc.bean.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	/**
	 * 获取用户列表
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDto> getList() {
		return userService.getList();
	}

	/**
	 * 新增用户
	 */
	@RequestMapping(method = RequestMethod.POST)
	public PageCodeDto add(UserDto userDto) {
		PageCodeDto result;
		if(userService.add(userDto)) {
			result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
		} else {
			result = new PageCodeDto(PageCodeEnum.USERNAME_EXISTS);
		}
		return result;
	}
	
	/**
	 * 根据主键获取用户dto
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public UserDto getById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public PageCodeDto modify(UserDto userDto) {
		PageCodeDto result;
		if(userService.modify(userDto)) {
			result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
		} else {
			result = new PageCodeDto(PageCodeEnum.USERNAME_EXISTS);
		}
		return result;
	}

	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public PageCodeDto remove(@PathVariable("id")Long id) {
		PageCodeDto result;
		if(userService.remove(id)) {
			result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
		} else {
			result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
		}
		return result;
	}

	/**
	 * 修改用户密码
	 */
	@RequestMapping(value = "/modifyPassword/{id}",method = RequestMethod.POST)
	public String modifyPassword(@PathVariable("id")String id, @RequestBody Password password){
		String psd = userService.getPasswordById(id);
		if(!MD5Util.getMD5(password.getOldPassword()).equals(psd)){
			return "false";
		}
		UserDto user = new UserDto();
		user.setPassword(password.getNewPassword());
		user.setId(Long.valueOf(id));
		userService.modify(user);
		return "redirect:/system/login";
	}


}