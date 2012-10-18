/*
 * Copyright (C) 2010 Dimitrios Menounos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package memo.web.portal.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mojo.dao.core.DataService;
import mojo.dao.model.user.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private DataService<User> userService;

	public DataService<User> getUserService() {
		return userService;
	}

	@Autowired
	@Qualifier("userService")
	public void setUserService(DataService<User> userService) {
		this.userService = userService;
	}

	@ResponseBody
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public User doFind(@RequestParam Integer id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void doUpdate(@RequestBody User user) {
		userService.update(user);
	}
}
