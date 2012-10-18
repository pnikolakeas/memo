package memo.web.admin.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import memo.web.admin.client.node.NodeActivity;
import memo.web.admin.client.node.NodePlace;

public class CmsActivityMapper implements ActivityMapper {

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof NodePlace) {
			return new NodeActivity();
		}

		return null;
	}
}
