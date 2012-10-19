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
package memo.web.portal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

public class PortalEntryPoint extends PortalClientFactory implements EntryPoint {

	public void onModuleLoad() {
		initClientFactory();
		initJS();

		initForum();
		initMembers();
	}

	private void initForum() {
		Anchor forum = Anchor.wrap(DOM.getElementById("menu-forum").getFirstChildElement());
		forum.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.preventDefault();

				Label moduleBaseURL = new Label("ModuleBaseURL: " + GWT.getModuleBaseURL());
				UIHelper.getMainContainer().add(moduleBaseURL);
			}
		});
	}

	private void initMembers() {
		Anchor users = Anchor.wrap(DOM.getElementById("menu-users").getFirstChildElement());
		users.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.preventDefault();

				Label hostPageBaseURL = new Label("HostPageBaseURL: " + GWT.getHostPageBaseURL());
				UIHelper.getMainContainer().add(hostPageBaseURL);
			}
		});
	}
}
