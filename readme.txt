

 
#How to run:
set browser=chrome  | config.properties

mvn clean test -DAUT=api -Dcucumber.options="src/main/java/featurefiles/api --glue stepdefinitions/api"
mvn clean test -DAUT=web -Dcucumber.options="src/main/java/featurefiles/web --glue stepdefinitions/web"
mvn clean test -DAUT=mobile -Dcucumber.options="src/main/java/featurefiles/mobile --glue stepdefinitions/mobile"


#Notes:
There should be 3 hooks files ie HooksWeb, HooksAPI, HooksMobile