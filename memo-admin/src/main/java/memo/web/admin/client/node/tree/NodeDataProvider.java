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
package memo.web.admin.client.node.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import mojo.gwt.data.client.DataStore;
import mojo.gwt.data.client.HttpStore;
import mojo.gwt.data.client.event.LoadEvent;
import mojo.gwt.data.client.event.LoadHandler;
import mojo.gwt.data.client.util.Observer;

public class NodeDataProvider extends AsyncDataProvider<Object> {

	private HttpStore store;
	private Object node;

	public NodeDataProvider(Object node) {
		// store = new NodeStore();
		this.node = node;
	}

	@Override
	public Object getKey(Object item) {
		if (item != null) {
//			GWT.log("getKey " + item.get("id"));
//			return item.get("id");
		}

		GWT.log("getKey");
		return null;
	}

	@Override
	protected void onRangeChanged(HasData<Object> display) {
		store.addLoadHandler(new OnLoad(display));
		Range range = display.getVisibleRange();

		StringBuilder sb = new StringBuilder("onRangeChanged");
		sb.append(" ").append(getKey(node));
		sb.append(" ").append(range.getStart());
		sb.append(" ").append(range.getLength());
		GWT.log(sb.toString());

		store.getParams().put("id", getKey(node));
		store.loadData();
	}

	private class OnLoad extends Observer implements LoadHandler {

		HasData<Object> display;

		OnLoad(HasData<Object> display) {
			this.display = display;
		}

		@Override
		public void onLoad(LoadEvent event) {
			DataStore store = (DataStore) event.getSource();

			if (store.getData().isEmpty()) {
				display.setRowCount(0, true);
			}
			else {
				Range range = display.getVisibleRange();
				display.setRowCount(store.getData().size(), true);
				display.setRowData(range.getStart(), store.getData());
			}

			getRegistration().removeHandler();
		}
	}
}
