package org.svenehrke.mybookmarks;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Data
public class BookmarkRequestStore {
	private FragmentHelper.CardModel cardModel;
}
