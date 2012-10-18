package memo.web.admin.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import memo.web.admin.client.node.NodePlace;

@WithTokenizers({ NodePlace.Tokenizer.class })
public interface CmsPlaceHistoryMapper extends PlaceHistoryMapper {
}
