@import java.util.List

@template._widget.addbookmark()

<div id="existing-tags" hx-swap-oob="innerHTML">
    @template._widget.existingtags(List.of("a", "b", "c"))
</div>

