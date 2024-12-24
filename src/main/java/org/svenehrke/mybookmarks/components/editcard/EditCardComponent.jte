@import org.svenehrke.mybookmarks.components.card.CardComponent
@import org.svenehrke.mybookmarks.components.editcard.EditCardTagsComponent
@import org.svenehrke.mybookmarks.components.editcard.EditCardComponent.Ctx
@import org.svenehrke.mybookmarks.components.editcard.PutBookmarkUrlAction

@param Ctx ctx

<bal-form-grid>
        <bal-form-col>
                <form
                        hx-put="${PutBookmarkUrlAction.url(ctx.id())}"
                        hx-target="${CardComponent.cssCardSelector(ctx.id())}"
                        hx-swap="innerHTML"
                >
                <bal-field>
                        <bal-field-control>
                                <bal-input name="url" placeholder="URL" value="${ctx.bookmarkSessionService().getById(ctx.id()).url()}"></bal-input>
                                <bal-button element-type="submit" color="primary">OK</bal-button>
                        </bal-field-control>
                </bal-field>
                </form>
                ${EditCardTagsComponent.ctx(ctx.bookmarkSessionService(), ctx.id())}
        </bal-form-col>

        <bal-form-col>
                <bal-button-group position="right" reverse="true">
                        <bal-button color="text"
                                    hx-trigger="click"
                                    hx-get="${CardComponent.uiUrl(ctx.id())}"
                                    hx-target="${CardComponent.cssCardSelector(ctx.id())}"
                                    hx-swap="innerHTML"
                        >
                                Close
                        </bal-button>
                </bal-button-group>
        </bal-form-col>

</bal-form-grid>
