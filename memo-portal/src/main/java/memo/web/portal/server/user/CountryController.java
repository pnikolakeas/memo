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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mojo.dao.core.DataPage;
import mojo.dao.core.DataService;
import mojo.dao.core.spec.Select;
import mojo.dao.model.user.Country;

@Controller
@RequestMapping("/user/country")
public class CountryController {

	private DataService<Country> countryService;

	public DataService<Country> getCountryService() {
		return countryService;
	}

	@Autowired
	@Qualifier("countryService")
	public void setCountryService(DataService<Country> countryService) {
		this.countryService = countryService;
	}

	@ResponseBody
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	public List<Country> doFetch() {
		Select<Country> spec = new Select<Country>().order("name");
		DataPage<Country> page = countryService.select(spec);
		covertCodesToUppercase(page.getData());
		return page.getData();
	}

	private void covertCodesToUppercase(List<Country> countries) {
		for (Country country : countries) {
			country.setCode(country.getCode().toUpperCase());
		}
	}
}