package com.github.galvo.bpcategory.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {"json:target/cucumber.json"},
  features = {"./src/test/resources/feature"}
)
public class BddTest {}
