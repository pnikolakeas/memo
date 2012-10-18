/*
 * Copyright (C) 2012 Dimitrios Menounos
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

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import mojo.gwt.ui.client.activity.ClientFactory;
import mojo.gwt.ui.client.content.ContentActivity;
import mojo.gwt.ui.client.content.ContentActivity.ContentPlace;

import memo.web.portal.client.profile.ProfileActivity;
import memo.web.portal.client.profile.ProfileActivity.ProfilePlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof ContentPlace) {
			ContentPlace contentPlace = (ContentPlace) place;
			return new ContentActivity(clientFactory, contentPlace);
		}

		if (place instanceof ProfilePlace) {
			ProfilePlace profilePlace = (ProfilePlace) place;
			return new ProfileActivity(clientFactory, profilePlace);
		}

		return null;
	}
}
