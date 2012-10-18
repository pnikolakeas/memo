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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import mojo.gwt.data.client.util.JSObject;
import mojo.gwt.i18n.client.JSConstants;
import mojo.gwt.ui.client.WebDialog;

import memo.gwt.common.client.model.UserModel;
import memo.web.portal.client.login.LoginContainer;
import memo.web.portal.client.login.ProfilePanel;

public class Session {

	public static final Messages msg = GWT.create(Messages.class);

	private static WebDialog dialog;

	/**
	 * Setup callback functions.
	 */
	public static native void initJS()
	/*-{
		$wnd.Session = {
			signIn: @memo.web.portal.client.Session::signIn(),
			signUp: @memo.web.portal.client.Session::signUp(Lmojo/gwt/data/client/util/JSObject;)
		};
	}-*/;

	public static void require() {
		// OpenIDPanel login = new OpenIDPanel();
		LoginContainer login = new LoginContainer();
		openDialog(login, msg.loginPanel());
	}

	/**
	 * Callback; finalizes the login process.
	 */
	protected static void signIn() {
		Window.Location.reload();
	}

	/**
	 * Callback; finalizes the register process.
	 */
	protected static void signUp(final JSObject jso) {
		ProfilePanel panel = new ProfilePanel(new UserModel(jso)) {

			@Override
			public void onSubmit() {
				Session.signIn();
			}

			@Override
			public void onCancel() {
				closeDialog();
			}
		};

		reopenDialog(panel, msg.profilePanel(), 500);
	}

	private static void closeDialog() {
		if (dialog != null) {
			dialog.hide();
		}
	}

	private static void openDialog(Widget widget, String text) {
		if (dialog == null) {
			dialog = new WebDialog();
			dialog.addCloseHandler(new CloseHandler<PopupPanel>() {

				@Override
				public void onClose(CloseEvent<PopupPanel> event) {
					dialog = null;
				}
			});
			dialog.setText(text);
			dialog.setWidget(widget);
			dialog.setAnimationEnabled(true);
			dialog.setGlassEnabled(true);
			// dialog.setModal(true);
			dialog.setClosable(true);
			dialog.center();
			dialog.show();
		}
	}

	private static void reopenDialog(final Widget widget, final String text, int time) {
		closeDialog();

		Timer timer = new Timer() {

			@Override
			public void run() {
				openDialog(widget, text);
			}
		};

		timer.schedule(time);
	}

	interface Messages extends JSConstants {

		String loginPanel();

		String profilePanel();
	}
}
