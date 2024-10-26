@import org.svenehrke.mybookmarks.components.existingtags.ExistingTagsComponent.Ctx

@param Ctx ctx

<bal-card-subtitle>All tags</bal-card-subtitle>
@template._widget.tags(tags = ctx.existingTags())
