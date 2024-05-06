package qageekweek;

import com.automation.remarks.video.annotations.Video;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIssues extends AbstractTestCase {

	@Value("${repository}")
	private String repo;
	
	@Value("${defaultUser}")
	private String user;
	
	@Value("${defaultPassword}")
	private String password;
	

	@Video
	@Test(description = "Test that when creating a new issue a single new issue is created")
	public void testCreateNewIssue() {
//		@formatter:off
		step("Performing login");
		val signIn  = introPage.clickOnSignInlnkAndGoToSignInPage();
		val mainPage = signIn
				.typeToUsernameOrEmailTb(user)
				.typeToPasswordTb(password)
				.clickOnSignInBtnAndGoToMainPage();
		
		step("Finding repository");
		val repoPage = mainPage.repositoriesWidget
			.typeToFindARepositoryTb(repo)
			.clickOnRepoAndGoToRepoCodePage(user, repo);
		
		step("Creating new issue");
		val issues = repoPage.clickOnIssuesLnkAndGoToIssuesPage();
		val newIssue = issues.clickOnNewIssueBtnAndGoToNewIssuePage();
		
		String issueTitle = "My new issue " + System.currentTimeMillis();
		
		val issue = newIssue
			.typeToTitle(issueTitle)
			.typeToComment("My issue comment")
			.clickOnSubmitIssueBtnAndGoToIssuePage();

		step("Asserting that issue was created");
		val issuesList = issue.clickOnIssuesLnkAndGoToIssuesPage();
		sleep(1);
		issuesList.typeToSearchTb(issueTitle);
		int numOfIssue = issuesList.getNumberOfIssues();
		assertThat(numOfIssue).isEqualByComparingTo(1);
//		@formatter:on

	}




}
