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
import mojo.dao.model.user.Language;

@Controller
@RequestMapping("/user/language")
public class LanguageController {

	private DataService<Language> languageService;

	public DataService<Language> getLanguageService() {
		return languageService;
	}

	@Autowired
	@Qualifier("languageService")
	public void setLanguageService(DataService<Language> languageService) {
		this.languageService = languageService;
	}

	@ResponseBody
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	public List<Language> doFetch() {
		Select<Language> spec = new Select<Language>().order("name");
		DataPage<Language> page = languageService.select(spec);
		covertCodesToLowerCase(page.getData());
		return page.getData();
	}

	private void covertCodesToLowerCase(List<Language> languages) {
		for (Language language : languages) {
			language.setCode(language.getCode().toLowerCase());
		}
	}
}
