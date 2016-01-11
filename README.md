# RestInPeace
A RestClient in Java (experimental)

RestInPeace is a fluent-wrapper over apache.httpclient for making REST calls(GET and POST) with JSON requests and responses.

Why do this:
 1. To ease making REST calls for GET and POST
 2. To understand apache's httpclient library better :)


## Build system
Gradle

## What does it support?
It supports GET and POST calls with JSON requests and responses

## Code samples
#### GET call

```
RestResponse<SimpleResponseDto> restResponse = new RestClient()
                .get()
                .withUrl(GET_URL)
                .withQueryParams(Maps.newHashMap())
                .withHeaders(Maps.newHashMap())
                .execute(SimpleResponseDto.class);
                
//  To get status code
int statusCode = restResponse.getStatusCode();

// To get response body
SimpleResponseDto responseDto = restResponse.getResponseBody();
```




#### POST call

```
RestResponse<SimpleResponseDto> restResponse = new RestClient()
                .post()
                .withUrl(POST_URL)
                .withQueryParams(Maps.newHashMap())
                .withHeaders(Maps.newHashMap())
                .withRequestBody(simpleRequestDto)
                .execute(SimpleResponseDto.class);
                
// To get status code
int statusCode = restResponse.getStatusCode();

// To get response body
SimpleResponseDto responseDto = restResponse.getResponseBody();
```


 

 
 
Please fill in with your suggestions, feedbacks and pull-requests :)
