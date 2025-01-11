package lld.ratelimiter;

public class RateLimitDemo {
    /*
        Rate Limiting

            Levels
                1. User Level
                2. API level
                3. Application level

            Core elements
                1. Identifier
                2. Window
                3. Limit

             Responses
                1. Dropping - takes place when requests exceeding the limit are denied access to the resource.
                              It is commonly expressed as an error message such as HTTP status code 429 (Too Many Requests).

                2. Shaping - allows requests that surpass the limit. But those requests are assigned lower priority.
                             This ensures that users who abide by the limits receive quality service.
                             For example, in a content delivery network, requests from users who have crossed their limits
                             may be processed last, while those from normal users are prioritized.

                3. Throttling - Involves slowing down or delaying the requests that go beyond the limit.
                                An example would be a video streaming service reducing the quality of the stream for users
                                who have gone over their data cap.


            Algorithms
                1. Fixed window counter - Per window limit of requests.
                2. Sliding window log - window consists of requests from current time minus window size, older than that requests are removed.
                3.
                4. Token bucket
                        a. Bucket size (With initial number of tokens)
                        b. Refill rate if tokens into bucket
                5. Leaky bucket
                        a. Bucket size - queue.
                        b. Rate at which to process requests in the queue.
     */
}
