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

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import mojo.gwt.data.client.util.JSObject;
import mojo.gwt.i18n.client.JSConstants;
import mojo.gwt.ui.client.activity.ClientFactory;
import mojo.gwt.ui.client.content.ContentActivity.ContentPlace;

import memo.gwt.common.client.model.UserModel;
import memo.web.portal.client.login.LoginContainer;
import memo.web.portal.client.login.ProfilePanel;

public class PortalClientFactory implements ClientFactory {

	public static final Messages msg = GWT.create(Messages.class);

	private EventBus eventBus;
	private ActivityMapper activityMapper;
	private ActivityManager activityManager;
	private PlaceController placeController;
	private PlaceHistoryMapper historyMapper;
	private PlaceHistoryHandler historyHandler;

	/**
	 * Setup callback functions.
	 */
	public static native void initJS()
	/*-{
		$wnd.obj.ns('session', {
			signIn: @memo.web.portal.client.PortalClientFactory::signIn(),
			signUp: @memo.web.portal.client.PortalClientFactory::signUp(Lmojo/gwt/data/client/util/JSObject;)
		});
	}-*/;

	/**
	 * Initializes the MVP framework.
	 */
	public void initClientFactory() {
		eventBus = new SimpleEventBus();

		// maps places to activities
		activityMapper = new AppActivityMapper(this);

		// swaps the current activity in response to PlaceChangeEvent
		activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(new AcceptsOneWidget() {

			@Override
			public void setWidget(IsWidget isWidget) {
				if (isWidget != null) {
					Panel mainContainer = UIHelper.getMainContainer();
					Element mainContainerEl = mainContainer.getElement();

					// clear previous widget
					mainContainer.clear();

					// clear previous non-widget elements
					while (mainContainerEl.hasChildNodes()) {
						Node tmp = mainContainerEl.getChild(0);
						mainContainerEl.removeChild(tmp);
					}

					Widget widget = isWidget.asWidget();
					Element widgetEl = widget.getElement();
					widgetEl.setId(getMainComponentId());

					// add current widget
					mainContainer.add(widget);
				}
			}
		});

		// maintains the current place
		// fires PlaceChangeEvent to trigger an activity swap
		placeController = new PlaceController(eventBus);

		// maps places to / from tokens
		historyMapper = GWT.create(AppPlaceHistoryMapper.class);

		// updates the history in response to PlaceChangeEvent
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new ContentPlace());
		historyHandler.handleCurrentHistory(); // fires initial PlaceChangeEvent
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public String getMainContainerId() {
		return UIHelper.MAIN_CONTAINER_ID;
	}

	@Override
	public String getMainComponentId() {
		return UIHelper.MAIN_COMPONENT_ID;
	}

	@Override
	public void requireLogin() {
		LoginContainer loginContainer = new LoginContainer();
		UIHelper.openDialog(loginContainer, msg.loginPanel());
	}

	/**
	 * Callback; finalizes the login process.
	 */
	public static void signIn() {
		Window.Location.reload();
	}

	/**
	 * Callback; finalizes the register process.
	 */
	public static void signUp(JSObject userJSO) {
		UserModel userModel = new UserModel(userJSO);
		ProfilePanel profilePanel = new ProfilePanel(userModel) {

			@Override
			public void onSubmit() {
				signIn();
			}

			@Override
			public void onCancel() {
				UIHelper.closeDialog();
			}
		};

		UIHelper.reopenDialog(profilePanel, msg.profilePanel());
	}

	public interface Messages extends JSConstants {

		String loginPanel();

		String profilePanel();
	}
}
