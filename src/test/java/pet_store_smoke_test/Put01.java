package pet_store_smoke_test;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.Category;
import pojos.PetStorePet;
import pojos.PetStoreUserResponsePojo;
import pojos.Tags;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Put01 extends PetStoreBaseUrl {
    /*
    Given
        https://petstore.swagger.io/v2/pet
    And
                       {
                  "id": 0,

                  "category": {
                    "id": 0,
                    "name": "doggie"
                  },

                  "name": "puppy",

                  "photoUrls": [
                    "string"
                  ],

                  "tags": [
                    {
                      "id": 0,
                      "name": "string"
                    }
                  ],

                  "status": "available"
                },
    When
        User sends Put request
    Then
        Status code is 200
    And
        Response body is like {
                                "id": 92233,

                                "category": {
                                    "id": 0,
                                    "name": "doggie"
                                },

                                "name": "puppy",

                                "photoUrls": [
                                    "string"
                                ],

                                "tags": [
                                    {
                                        "id": 0,
                                        "name": "string"
                                    }
                                ],

                                "status": "available"
                            }
     */
    @Test
    public void put01() throws IOException {
        //set the url
        spec.pathParam("first", "pet");

        //set the expected data
        Category category=new Category(0,"doggie");

        Tags tags=new Tags(0, "string");

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("string");

        ArrayList<Tags> arrayListTags=new ArrayList<>();
        arrayListTags.add(tags);

        PetStorePet expectedData = new PetStorePet(0,category,"puppy",arrayList,arrayListTags,"available");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the respone
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}");
        response.prettyPrint();

        //do assertion
        PetStorePet actualData=new ObjectMapper().readValue(response.asString(),PetStorePet.class);
        System.out.println("actualData = " + actualData);




    }

}
