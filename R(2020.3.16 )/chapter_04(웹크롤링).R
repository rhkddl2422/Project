#
# 데이터 수집(web scraping, web crawling)

# 웹사이트 상에서 내가 원하는 위치에 대한 정보를
# 자동으로 추출해서 수집하는 기능 => web scraping

# 자동화 봇인 web crawler가 정해진 규칙에 따라서
# 복수개의 웹페이지를 browsing하는 행위

# web scraping => CSS,jQuery(selector)
#                 XPATH를 이용하는 방식

# selector를 가지고 웹 스크래핑을 알아보아요!!
# css로 selector를 알아보아요!!
# selector가 어떤 형식의 표기법인지를 파악!!

# 네이버영화 댓글 페이지를 이용해서 scraping을
# 해 보아요!!
# 필요한 package부터 설치해 보아요!!
install.packages("rvest")
library(rvest)
library(stringr)
# scraping할 페이지의 url을 알아보아요!!
url <- "https://movie.naver.com/movie/point/af/list.nhn?&page="

request_url <- str_c(url,1)
request_url

page_html <- read_html(request_url, encoding="CP949")
page_html # url을 이용해서 결과 HTML 코드를 받아와요!

# html중에 selector에 맞는 element를 가져와요!
nodes = html_nodes(page_html,"td.title > a.movie")

# element가 가지고 있는 text(tag사이의 글자)를 얻어와요
movie_title <- html_text(nodes)

# 첫번째 page의 영화제목을 구해왔어요!!
movie_title

# 첫번째 page의 평점과 코멘트도 알아와야 해요!!
# 이 부분은 각자 작업해 보아요!!
nodes = html_nodes(page_html,"td.title > div > em")
movie_rate <- html_text(nodes)
movie_rate

nodes = html_nodes(page_html,"td.title")
movie_content <- html_text(nodes)
movie_content = str_remove_all(movie_content,"\t")
movie_content = str_remove_all(movie_content,"\n")
movie_content = str_remove_all(movie_content," 신고")

####################################################

# 4일차

# R의 데이터 타입, 자료구조
# R의 데이터 수집
# - 파일로부터 데이터를 읽어들이는 방법
# - Web Application을 통한 데이터 획득(JSON)
# - web scraping을 통해서 web page로 부터 데이터를 획득
#   => selector를 이용해서 처리해봤어요!
#   => (오늘) xpath를 이용한 처리

# 수행평가 문제를 하나 풀어보아요! (web scraping)

# 데이터 조작
# data frame을 조작하는 방식
# 간단한 예제를 통해서 조작하는 방법을 알아보고
# 문제를 제공

install.packages("rvest")  # web scraping을 위한 package를 설치
library(rvest) # require(rvest) package를 메모리에 로딩
library(stringr)
# 네이너 영화 comment 페이지에서 영화제목, 평점, 리뷰

url = "https://movie.naver.com/movie/point/af/list.nhn?&page="

# 추후에 crawling작업을 생각해서 request_url을 작성
request_url <- str_c(url,1)

# url을 이용해서 해당 페이지의 HTML내용을 가져와요!!
page_html <- read_html(request_url, encoding = "CP949")

# 영화제목부분을 추출하기 위해서 제목을 가지고 있는
# HTML element를 획득(selector를 이용)
# nodes = html_nodes(page_html, "td.title > a.movie")

# 영화제목을 저장할 빈 vector를 생성
movie_title = vector(mode="character", length=10)

for(i in 1:10) {
  myPath = str_c('//*[@id="old_content"]/table/tbody/tr[',
                 i,
                 ']/td[2]/a[1]/text()')
  nodes = html_nodes(page_html, xpath=myPath)
  movie_title[i] = html_text(nodes)
}

movie_title
#####################################################

# 수행평가문제!
# 로튼토마토 사이트에가서 2019년에 가장 인기있었던
# 영화 100개의 제목, User Rating, Genre를 추출해서
# data frame으로 생성한 후 파일로 저장!

# rank titles score genre
# 1 parasite 90% Art House & International, Drama
# 2 ..

install.packages("rvest")
require(rvest)
require(stringr)

url = "https://www.rottentomatoes.com/top/bestofrt/?year=2019"

# 전체 page의 HTML
html_page = read_html(url, encoding = "UTF-8")

# HTML내에서 내가 원하는 element(node)를 선택
node = html_node(html_page,xpath='//*[@id="top_movies_main"]/div/table/tr[1]/td[3]/a')

node
myLink <- html_attr(node,"href")
myLink

real_link = str_c("https://www.rottentomatoes.com",myLink)
real_link

first_movie_html_page = read_html(real_link, encoding = "UTF-8")
first_movie_html_page

first_movie_title_node = html_node(first_movie_html_page,xpath='//*[@id="topSection"]/div[2]/div[1]/h1')
first_movie_title_node
html_text(first_movie_title_node)

#######################################################

# 데이터 조작
# SQL처럼 빅데이터 내에서 필요한 정보를 추출하는 방법

# 기본적인 함수에 대해서 알아야 해요!!

# ggplot2 package => 그래프를 그리는 package
# 이 package안에 mpg data set이 있어요!


install.packages("ggplot2")
require(ggplot2)
# table형식
mpg
# data frame으로 변환
df = as.data.frame(mpg)
View(df)

# 기본적인 함수부터 알아봐요!!
# 1. class() : 자료구조를 알고 싶을때=>자료구조가 문자열로 출력
class(df)
# 2. ls() : data frame에 대해서 적용되면 컬럼을 출력(vector형태)
ls(df)
# 3. head() : 데이터의 앞쪽 6개만 출력
head(df)
# 4. tail() : 데이터의 뒤쪽 6개만 출력
tail(df)
# 5. View() : View창을 이용해서 데이터를 출력
View(df)
# 6. dim() : 행과 열의 개수를 알려줘요!
dim(df)
# 7. nrow() : 행의 개수
nrow(df)
# 8. ncol() : 열의 개수
ncol(df)
# 9. str() : data frame의 전반적인 정보를 출력
str(df)
# 10. length() : 개수를 구하는 함수인데.. data frame에
#                적용시키면 column의 개수를 알려줘요!
length(df)
# 11. summary() : 간단한 통계치를 보여줘요! (숫자에 대해서)
summary(df)

#### 여기까지는 기본함수예요!!

#### data frame을 제어해서 원하는 정보를 추출하려면
#### 특수한 package를 사용하는게 좋아요!!

#### 가장 많이 사용되는 data frame제어 package는 "dplyr"

install.packages("dplyr")
library(dplyr)
library(xlsx) # excel파일을 이용하기 위한 package

# dplyr을 이용해서 data frame을 제어해 보아요!
# data frame을 하나 가지고 올꺼예요!!

excel_data = read.xlsx(file.choose(),
                       sheetIndex = 1,
                       encoding = "UTF-8")
excel_data
View(excel_data)  

# dplyr는 data frame을 제어하는데 특화된 함수를 제공해요!!
# chaining을 지원하기 때문에 편하게 data frame을 제어할 수
# 있어요 ( %>% )

# 1. rename(data frame, newVar = var)
#    : 컬럼명을 변경할 수 있어요!!
df = rename(excel_data,
            Y17_AMT = AMT17,
            Y16_AMT = AMT16)
df  

# 2. filter(data frame, 조건1, 조건2, ...)
df = filter(excel_data,
            SEX == "M",
            AREA == "서울")
df

df = filter(excel_data,
            SEX == "M",
            AREA %in% c("서울","경기"))
df

# 3. arrange(data frame,column명,desc(column명))
#    기본적으로 정렬은 오름차순 정렬
#    내림차순 정렬하려면 desc()를 이용
df = arrange(excel_data,
             SEX,
             desc(AGE))
df

## 성별이 남성인 사람들만 찾아서 나이순(내림차순)으로 정렬해서
## 출력하세요!!
df = filter(excel_data,
            SEX=="M") %>%
     arrange(desc(AGE))
df

# 4. select(data frame, 컬럼명, 컬럼명, 컬럼명, ...)
df = filter(excel_data,
            SEX=="M") %>%
     arrange(desc(AGE)) %>%
     select(ID, SEX, AREA)
df

df = filter(excel_data,
            SEX=="M") %>%
     arrange(desc(AGE)) %>%
     select(-SEX)
df

# 5. mutate(data frame,column명=수식,column명=수식,...)
#    원본 data frame에 대해서
#    남자면서 AMT17의 값이 100,000이상인 사람을 찾아서
#    이람들을 VIP로 설정
df =  filter(excel_data,
             SEX == "M") %>% 
      mutate(VIP = AMT17 > 500000)
df

# 6. summarise(data frame,추가할 column명=함수, column명=함수)
df = summarise(excel_data,
               SUM17AMT=sum(AMT17), cnt=n())
df

# 7. group_by(data frame, 범주형 column)
df = group_by(excel_data,
              SEX) %>%
     summarise(SUM17AMT=sum(AMT17), cnt=n())
df

# 8. bind_rows(df1, df2)
df1 <- data.frame(x=c(1,2,3))
df1
df2 <- data.frame(y=c(4,5,6))
df2
bind_rows(df1,df2)

####################################################
# 우리 dplyr을 배웠으니
# 연습문제를 풀어보아요!!
# data set은 mpg(자동차 연비데이터)를 이용

# EDA(탐색적 데이터 분석)
# 공유폴더에 "dplyr 연습문제(0312).txt" 파일이 있어요!!
# 해당내용을 RStudio에 복사하신다음에 하나하나 해결을
# 해 보시면 됩니다.!!

# ggplot2 package의 mpg data 활용

install.packages("ggplot2")
library(ggplot2)

mpg = as.data.frame(mpg)   # mpg data frame

View(mpg)

# 주요컬럼
# manufacturer : 제조회사
# displ : 배기량
# cyl : 실린더 개수
# drv : 구동 방식
# hwy : 고속도로 연비
# class : 자동차 종류
# model : 자동차 모델명
# year : 생산연도
# trans : 변속기 종류
# cty : 도시 연비
# fl : 연료 종류

# 1. 자동차 배기량에 따라 고속도로 연비가 다른지 알아보려 한다. 
# displ(배기량)이 4 이하인 자동차와 4 초과인 자동차 중 
# 어떤 자동차의 hwy(고속도로 연비)가 평균적으로 더 높은지 확인하세요.

#결과
#배기량4이하    26.0
#배기량4초과    17.6

# 2. 자동차 제조 회사에 따라 도시 연비가 다른지 알아보려고 한다. 
# "audi"와 "toyota" 중 어느 manufacturer(제조회사)의 cty(도시 연비)가 
# 평균적으로 더 높은지 확인하세요.

#결과
# audi     17.6
# toyota   18.5


# 3. "chevrolet", "ford", "honda" 자동차의 고속도로 연비 평균을 알아보려고 한다. 
# 이 회사들의 데이터를 추출한 후 hwy(고속도로 연비) 전체 평균을 구하세요.

# 결과
# 22.50943


# 4. "audi"에서 생산한 자동차 중에 어떤 자동차 모델의 hwy(고속도로 연비)가 
# 높은지 알아보려고 한다. "audi"에서 생산한 자동차 중 hwy가 1~5위에 해당하는 
# 자동차의 데이터를 출력하세요.

# 결과
# audi  a4 2.0 2008 4 manual(m6) f 20 31 p compact
# audi  a4 2.0 2008 4 auto(av)   f 21 30 p compact
# audi  a4 1.8 1999 4 auto(l5)   f 18 29 p compact
# audi  a4 1.8 1999 4 manual(m5) f 21 29 p compact
# audi  a4 quattro 2.0 2008 4 manual(m6) f 20 28 p compact


# 5. mpg 데이터는 연비를 나타내는 변수가 2개입니다. 
# 두 변수를 각각 활용하는 대신 하나의 통합 연비 변수를 만들어 사용하려 합니다. 
# 평균 연비 변수는 두 연비(고속도로와 도시)의 평균을 이용합니다. 
# 회사별로 "suv" 자동차의 평균 연비를 구한후 내림차순으로 정렬한 후 1~5위까지 데이터를 출력하세요.

# 결과
# subaru    21.9
# toyota    16.3
# nissan    15.9
# mercury   15.6
# jeep      15.6


# 6. mpg 데이터의 class는 "suv", "compact" 등 자동차의 특징에 따라 
# 일곱 종류로 분류한 변수입니다. 어떤 차종의 도시 연비가 높은지 비교하려 합니다. 
# class별 cty 평균을 구하고 cty 평균이 높은 순으로 정렬해 출력하세요.

# 결과
# subcompact  20.4
# compact     20.1
# midsize     18.8
# minivan     15.8
# 2seater     15.4
# suv         13.5
# pickup      13


# 7. 어떤 회사 자동차의 hwy(고속도로 연비)가 가장 높은지 알아보려 합니다. 
# hwy(고속도로 연비) 평균이 가장 높은 회사 세 곳을 출력하세요.

# 결과
# honda
# volkswagen
# hyundai


# 8. 어떤 회사에서 "compact" 차종을 가장 많이 생산하는지 알아보려고 합니다. 
# 각 회사별 "compact" 차종 수를 내림차순으로 정렬해 출력하세요.

# 결과
# audi        15
# volkswagen  14
# toyota      12
# subaru      4
# nissan      2

#####################################################

# 데이터 정제!!

# 데이터 분석이전에 raw데이터(현장에서 수집한 데이터)를
# 분석이 가능한 형태로 가공하는 작업이 선행되어야 해요!

# 1. 결측치를 해결해야 해요!
# NA

# NA를 처리하기 위해서 우리가 사용하는 함수
# is.na()

df <- data.frame(id=c(1,2,NA,4,NA,6),
                 score=c(20,30,NA,50,70,NA))
df

is.na(df)
is.na(df$id)  # vector
is.na(df$score)  # vector

# NA가 들어가 있는 데이터가 전체 데이터크기에 비해
# 상대적으로 아주 작을때는 삭제하는게 좋을 수 있어요!

library(dplyr)  # data frame을 가장 쉽고 편하게 제어할 수 있는
                # package

# id가 NA인 row를 data frame에서 삭제할꺼예요!
result <- df %>%
          filter(!is.na(df$id))    
result

result <- df %>%
          filter(!is.na(df$id),
                 !is.na(df$score))    
result
# data frame에서 모든 NA를 삭제할 수 있어요!!

# 조금 더 쉽게 모든 NA를 다 찾아서 NA가 포함된 row를 삭제하는
# 함수가 있어요!

result <- na.omit(df)
result

## NA값을 무작정 삭제하는건 그다지 바람직한 방법은 아니예요!!
## NA값을 다른 값으로 대체해서 사용하는게 좋아요!
## NA값을 해당컬럼의 평균값, 최소값, 최대값, 등등등..이용해서
## 적절한 값으로 대체

df <- data.frame(id=c(1,2,NA,4,NA,6),
                 score=c(20,30,NA,50,70,NA))
df

mean(df$score)
## 일반적으로 NA를 무시하고 연산하는 방법을 제공
mean(df$score, na.rm = TRUE)

## score의 NA값을 score안에 있는 NA를 제거한 모든 값의 평균으로
## 대체해서 새로운 data frame을 만들어 사용할 꺼예요!!

df$score <- ifelse(is.na(df$score),
                   mean(df$score, na.rm = TRUE),
                   df$score)
df
#####################################
## 결측치를 해결했으면
## 그 다음 생각해야 하는게 이상치

## 2. 이상치는 값이 이상한거예요!
##    - 값이 없는건 아닌데 사용할 수 없는 값이 포함된 경우
df <- data.frame(id=c(1,2,NA,4,NA,6),
                 score=c(20,30,NA,50,70,NA),
                 gender=c("^^","F","M","F","M","F"),
                 stringsAsFactors = FALSE)
df

# 이상치는 일단 NA로 변환한 다음 NA를 처리하는 방식으로 진행
df$gender <- ifelse(df$gender %in% c("M","F"),
                    df$gender,
                    NA)
df

## 2. 이상치는 값이 이상한거예요!
##    - 값이 없는건 아닌데 사용할 수 없는 값이 포함된 경우
##    - 극단치값이 포함되어 있는 경우 이 값을 해결.
##      극단치에 대한 기준을 우리가 설정!
##      이 기준을 잡는 방법은 논리적으로 이상적인 범위를 설정
##      통계적인 방법으로 이상적인 범위를 설정

