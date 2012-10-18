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

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.TreeViewModel;

public class NodeTreeViewModel implements TreeViewModel {

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		GWT.log("getNodeInfo " + value);
		Object node = (Object) value;
		NodeDataProvider dataProvider = new NodeDataProvider(node);
		return new DefaultNodeInfo<Object>(dataProvider, new NodeCell());
	}

	@Override
	public boolean isLeaf(Object value) {
		return false;
	}

	private static class NodeCell extends AbstractCell<Object> {

		@Override
		public void render(Context context, Object value, SafeHtmlBuilder sb) {
//			if (value != null && value.get("name") != null) {
//				sb.appendEscaped(value.get("name").toString());
//			}
		}
	}
}
