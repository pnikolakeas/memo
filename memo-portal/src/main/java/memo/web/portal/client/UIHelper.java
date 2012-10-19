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

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import mojo.gwt.ui.client.WebDialog;

public class UIHelper {

	public static final String MAIN_CONTAINER_ID = "contentContainer";
	public static final String MAIN_COMPONENT_ID = "contentComponent";

	private static WebDialog dialog;

	public static Panel getMainContainer() {
		return RootPanel.get(MAIN_CONTAINER_ID);
	}

	public static void closeDialog() {
		if (dialog != null) {
			dialog.hide();
		}
	}

	public static void openDialog(Widget widget, String text) {
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

	public static void reopenDialog(final Widget widget, final String text) {
		closeDialog();

		Timer timer = new Timer() {

			@Override
			public void run() {
				openDialog(widget, text);
			}
		};

		timer.schedule(500);
	}
}
