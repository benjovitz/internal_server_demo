# answering exam questions
### What are the essential factors to take into account when designing a system that incorporates several external APIs?
efficiency is important, u dont want to slow your application down, so we need to make sure that the for example: have parralel calls to the external api's, optimizing the application
knowing what the returned value is coming as, not everything is returned as JSON and we need to be prepared for different types of data values.
The price of the external API calls, we want to make sure that we can optimize the cost of running the application, like caching the values we get from the external api's
### How can you optimize request handling in an application that relies heavily on communication with external services?
parrallel calls for the external api's, if there is multiple calls to external api's, we can do them in parralel fashion, and mimimize waiting time.
caching the answers, we can cache answers, which can benifit us to ways. <br> 1: we can get faster answers directly from out database, instead of collecting info from external api's again. <br> 2: we reduce the cost by not having to call the api's again and just reusing and answer for something we already have called before.
### Explain how Tomcat handles additional request, even if a previous request is blocked waiting for a response from an external endpoint
Tomcat will assign a new thread to handle the new http request for efficiancy
### Discuss blocking vs. non-blocking strategies for external API calls, and when/why to use one or the other
blocking is when the application stops and waits for the blocked section to be done, whereas noneblocking will continue.
nonblocking will be more efficient, but can lead to issues if you need wait for some data and the application will continue without it
blocking will be less efficient, but can be usefull if we need to wait for some data before continuing
### Describe why caching can be important, in systems relying on external endpoints. 
caching the answers, we can cache answers, which can benifit us to ways. <br> 1: we can get faster answers directly from out database, instead of collecting info from external api's again. <br> 2: we reduce the cost by not having to call the api's again and just reusing and answer for something we already have called before.
