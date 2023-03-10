package pet_store_smoke_test;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.Category;
import pojos.PetStorePet;
import pojos.Tags;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends PetStoreBaseUrl {
    /*
    Given
        https://petstore.swagger.io/v2/pet
    And
        {
          "id": 1234321,

          "category": {
            "id": 0,
            "name": "Cat"
          },
          "name": "Pamuk",

          "photoUrls": [
            "string"
          ],

          "tags": [
            {
              "id": 0,
              "name": "My cute cat"
            }
          ],

          "status": "available"
        }
    When
        User sends Post request

    Then
        Http Status code is 200

    And
        Response body is like: {
                                "id": 1234321,

                                "category": {
                                    "id": 0,
                                    "name": "Cat"
                                },

                                "name": "Pamuk",

                                "photoUrls": [
                                    "string"
                                ],

                                "tags": [
                                    {
                                        "id": 0,
                                        "name": "My cute cat"
                                    }
                                ],

                                "status": "available"
                            }
     */
    @Test
    public void post01() throws IOException {
        //Set the url
        spec.pathParam("first","pet");

        //Set the expected data
        Category category = new Category(0,"Cat");
        Tags tags = new Tags(0,"My cute cat");

        ArrayList<String > arrayList = new ArrayList<>();
        arrayList.add("string");

        ArrayList<Tags> arrayListTags = new ArrayList<>();
        arrayListTags.add(tags);

        PetStorePet expectedData = new PetStorePet(1234321,category,"Pamuk",arrayList,arrayListTags,"available");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assertion
        PetStorePet actualData=new ObjectMapper().readValue(response.asString(),PetStorePet.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getCategory().getName(), actualData.getCategory().getName());
        assertEquals(expectedData.getCategory().getId(), actualData.getCategory().getId());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getPhotoUrls(), actualData.getPhotoUrls());
        //assertEquals(Tags.);
        assertEquals(expectedData.getStatus(), actualData.getStatus());






    }

}