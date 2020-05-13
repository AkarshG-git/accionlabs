# accionlabs

Files need to update before running the suite
1. In Accionlabs_Automation\src\main\resources\properties_files\config\common.properties - update the browser and run.platform 
values accordingly.
2. Update the android device details here - accionlabs\Accionlabs_Automation\src\main\resources\properties_files\config\android.properties
3. Update the ios device details here - accionlabs\Accionlabs_Automation\src\main\resources\properties_files\config\ios.properties

To run the suite

1. for mobile device browser - right click on the file Mobile.xml and run as TestNg Suite.
2. for desktop web browser - right click on the file Web.xml and run as TestNg Suite.

To check the report 

1. For Mobile Suite - go to the folder test-output > MobileSuite : MobileTest.html
2. For Web Suite - go to the folder test-output > WebSuite : WebTest.html
3. For the good view of the reports : immediate after running any suite (mobile/web), go to the folder test-output > ExtentReport : Extent.html
