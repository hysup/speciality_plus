<div align= "center">
    <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&height=180&text=주특기%20플러스 개선과제%20&animation=fadeIn&fontColor=000000&fontSize=60" />
    </div>
    <div align= "center"> 
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;">  </h2>  
    <div style="font-weight: 700; font-size: 15px; text-align: center; color: #282d33;">  </div> 
    </div>
    <div align= "center">
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🛠️ Tech Stacks </h2> <br> 
    <div style="margin: 0 auto; text-align: center;" align= "center"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
          <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=기술스택아이콘&logoColor=white">
          </div>
    </div>
    <div align= "center">
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🧑‍💻 Contact me </h2> <br> 
    <div align= "center"> <a href=https://valar01.tistory.com/> <img src="https://img.shields.io/badge/Tistory-000000?style=for-the-badge&logo=Tistory&logoColor=white&link=https://valar01.tistory.com/"> </a>
          </div>  <br> 
    <div align= "center">  </div> 
    </div>
    <div align= "center"> 
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🏅 Stats </h2> <div align= "center"> <img src="https://github-readme-stats.vercel.app/api?username=hysup&custom_title=hysup's Github Stat&bg_color=180,000000,&title_color=000000&text_color=000000"
        /> <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=hysup&layout=compact&bg_color=180,000000,&title_color=000000&text_color=000000"
           /> </div> 
    </div>


### 기술 스택
언어: kotlin

프레임워크: Spring Boot

데이터베이스: h2

인증: JWT(Json Web Token)



## 🚀 개선사항 
1. Controller Advice 로 예외 공통화 처리하기
````@RequestMapping("/api/v1/posts")
@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/search")
    fun searchPostList(@RequestParam(name = "title") title: String): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.searchPostList(title))
    }
````

### 2. Spring AOP 적용

- Spring AOP 를 사용하여 부가기능을 추가
``@Aspect
  @Component
  class StopWatchAspect {

        private val logger = org.slf4j.LoggerFactory.getLogger("Execution Time Logger")


        @Around("@annotation(com.example.kotlin25.config.aop.StopWatch)")
        fun run(joinPoint: ProceedingJoinPoint) {
            val stopWatch = StopWatch()

            stopWatch.start()
            joinPoint.proceed()
            stopWatch.stop()

            val timeElapsedMs = stopWatch.totalTimeMillis

            val methodName = joinPoint.signature.name
            val methodArguments = joinPoint.args

            logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} | Execution Time: ${timeElapsedMs}ms")
        }
  ``
### 3. QueryDSL 을 사용하여 검색 기능 만들기

- QueryDSL 의 jpaQueryFactory 를 사용해서 검색기능을 구현 
````@Repository
class PostRepositoryImpl:CustomPostRepository, QueryDslSupport() {
    private val post = QPost.post
    override fun searchByPostListByTitle(title: String): List<Post> {
        return queryFactory.selectFrom(post) // queryFactory를 사용하여 QueryDSL 쿼리를 시작
            .where(post.title.containsIgnoreCase(title)) // 제목에 대해 대소문자를 무시하고 지정된 문자열이 포함된 경우 필터링
            .fetch() // 쿼리를 실행
    }
````

### 4. Pageable 을 사용하여 페이징 및 정렬 기능 만들기

- Pageable 을 사용해서 원하는 페이지 사이즈만큼만 조회
````
fun findByPageable(pageable: Pageable):Page<Post>
````

````
    override fun findByPageable(pageable: Pageable): Page<Post> {

        val totalCount = queryFactory.select(post.count()).fetchOne() ?: 0L
        val query = queryFactory.select(post)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        if (pageable.sort.isSorted) {
            when(pageable.sort.first()?.property) {
                "id" -> query.orderBy(post.id.asc())
                "title" -> query.orderBy(post.title.asc())
                else -> query.orderBy(post.id.asc())
            }
            }else {
                query.orderBy(post.id.asc())
            }
        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCount)
    }
}
````

### 🙏 마무리
챕터2 페이징 및 정렬기능에 상태를 나타내는 status도 들어가 있었지만 게시글의 상태를 굳이 boolean을 써서 나타낼 필요는 없다고 생각해서 제외하게 되었습니다. 
그 외에 동적쿼리와 테스트코드도 시도해보고 싶었으나 복습과제에서 생각보다 시간을 많이 들이게 되어 구현하지 못해 아쉬웠습니다. 

하지만 과제를 시작하기전에 앞서 강의를 통해 테스트코드 작성하는 것을 숙지하였고 저는 주로 시간을 나타낼때 zonedDatetime을 사용하는데 이를 테스트 코드에 적용을 해보니 javatimemodule 설정이 안되는 오류가 있었고 objectMapper 적용해 해결 할 수 있었습니다. 

````
val objectMapper = ObjectMapper().apply {
                    registerModule(JavaTimeModule())
                    registerModule(KotlinModule()) 
                    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                }
````
