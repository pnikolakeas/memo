package memo.web.admin.client.node;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NodePlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<NodePlace> {

		@Override
		public NodePlace getPlace(String token) {
			return new NodePlace();
		}

		@Override
		public String getToken(NodePlace place) {
			return null;
		}
	}
}
