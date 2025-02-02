package com.test;


import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
  features = {"classpath:features/compareLists.feature"},
  glue={"stepDefinition"}
  )

public class TestRunner {

}
