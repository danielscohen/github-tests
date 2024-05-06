package qageekweek.po;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;

public class MainPage extends AbstractPage {

	private final static By repositoriesWidgetRootBy = DBy.id("dashboard-repos-container","Repositories Widget Root");

	public RepositoriesWidget repositoriesWidget;
	
	public MainPage(ActionBot bot) {
		super(bot);
		repositoriesWidget = new RepositoriesWidget(new ActionBot(bot, repositoriesWidgetRootBy));
	}

	@Override
	protected void assertInPage() {
		bot.waitForVisible(repositoriesWidgetRootBy);
	}

}
