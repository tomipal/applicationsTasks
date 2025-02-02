package stepDefinition;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

public class CompareListsFeature {
	private List<Map<String, String>> firstList;
	private List<Map<String, String>> secondList;

	@Given("I have the following items in the first list:")
	public void i_have_the_following_items_in_the_first_list(io.cucumber.datatable.DataTable dataTable) {
		firstList = convertDataTableToList(dataTable);
	}

	@Given("I have the following items in the second list:")
	public void i_have_the_following_items_in_the_second_list(io.cucumber.datatable.DataTable dataTable) {
		secondList = convertDataTableToList(dataTable);
	}

	@When("I compare both lists")
	public void i_compare_both_lists() {
	}

	@Then("the lists should contain the same items with name, price, and category, regardless of order")
	public void the_lists_should_contain_the_same_items_with_name_price_and_category_regardless_of_order() {
		assertTrue("The lists do not contain the same items", compareLists(firstList, secondList));
	}

	private List<Map<String, String>> convertDataTableToList(DataTable dataTable) {
		List<Map<String, String>> list = new ArrayList<>();
		List<String> headers = dataTable.row(0);
		List<List<String>> rows = dataTable.asLists(String.class);

		for (int i = 1; i < rows.size(); i++) {
			List<String> row = rows.get(i);
			Map<String, String> map = new HashMap<>();
			for (int j = 0; j < headers.size(); j++) {
				if (j < rows.size() - 1) {
					map.put(headers.get(j), row.get(j));
				} else {
					map.put(headers.get(j), "");
				}
			}
			list.add(map);
		}
		return list;
	}

	private boolean compareLists(List<Map<String, String>> list1, List<Map<String, String>> list2) {
		boolean allMatch = true;
		
		for (Map<String, String> item1 : list1) {
			boolean found = false;
			for (Map<String, String> item2 : list2) {
				if (item1.get("name").equals(item2.get("name"))) {
					found = true;
					if (!item1.get("price").equals(item2.get("price"))) {
						System.out.println("Price mismatch for item '" + item1.get("name") + "': " + "List 1 price = "
								+ item1.get("price") + ", List 2 price = " + item2.get("price"));
						allMatch = false;
					}
					if (!item1.get("category").equals(item2.get("category"))) {

						System.out.println(
								"Category mismatch for item '" + item1.get("name") + "': " + "List 1 category = "
										+ item1.get("category") + ", List 2 category = " + item2.get("category"));
						allMatch = false;
					}
					break;
				}
			}

			if (!found) {
				System.out.println("Item missing in second list: " + item1);
				allMatch = false;
			}
		}

		for (Map<String, String> item2 : list2) {
			boolean found = false;
			for (Map<String, String> item1 : list1) {
				if (item2.get("name").equals(item1.get("name"))) { 
					found = true;
				}
			}

			if (!found) {
				System.out.println("Item missing in first list: " + item2);
				allMatch = false;
			}
		}

		return allMatch;
	}
}
