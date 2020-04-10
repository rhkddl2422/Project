# 빅데이터 처리에서 문자열처리는 꽤나 빈번하게 
# 발생되는 작업이예요!!

# 기본적인 R의 base system을 이용해서 문자열을 처리하는건
# 그다지 좋은 방식은 아니예요!!

# 문자열처리에 대한 대표적인 package가 있어요!
# stringr 

# 1. package설치
install.packages("stringr")
# 2. package loading
library(stringr)  # require(stringr)

# 3. 문자열(character)을 하나 준비해보아요!!
myStr = "Hongkd1051Leess4504YOU25홍길동1004"

# 해당문자열을 이용해서 stringr이 제공하는 함수들의
# 기능을 알아보아요!!

# 4. 문자열의 길이를 알아보아요!!
str_length(myStr)    # 31

# 5. 찾고자 하는 문자열의 시작위치와 끝위치를 알려줘요
str_locate(myStr,"Lee") 
str_locate(myStr,"0") 
str_locate_all(myStr,"0") 

# 6. 부분문자열
myStr = "Hongkd1051Leess4504YOU25홍길동1004"
str_sub(myStr,2,5)

# 7. 모두 대문자로, 모두 소문자로
str_to_upper(myStr)  # 모두 대문자로
str_to_lower(myStr)  # 모두 소문자로

# 8. 문자열 교체
str_replace(myStr,"홍길동","신사임당")

# 9. 문자열 결합
str_c(myStr,"이순신2020")

# 10. 문자열 분리
myStr = "Hongkd1051,Leess4504,YOU25,홍길동1004"
str_split(myStr,",")  # vector로 return

########################################

# R 정규식(regular expression)
# 약속된(정해져있는)기호를 이용해서 의미를 표현

myStr = "Hongkd1051,Leess4504,YOU25,홍길동1004"

# stringr에 정규식을 이용해서 내가 원하는 형태의
# 문자열을 추출하는 함수가 있어요!!

str_extract_all(myStr,"[a-z]{3}")  # ong ees
str_extract_all(myStr,"[a-z]{3,}") # ongkd eess
str_extract_all(myStr,"[a-z]{3,4}") # ongk eess

str_extract_all(myStr,"[가-힣]{3}") # 홍길동
str_extract_all(myStr,"[0-9]{4}") # 1051 4504 1004

myStr = "Hongkd1051,Leess4504,YOU25,홍길동1004"
str_extract_all(myStr,"[^a-z]{3}")  # 105 1,L 450 4,Y
# OU2 5,홍 길동1 004

####################################################


# 데이터의 입출력

# 데이터 분석을 하기 위해서는 가장 먼저 데이터를 
# 준비해야 해요!!
# 키보드로 부터 데이터를 입력받아 보아요!!
# scan() edit() 함수를 이용해서 입력을 받아보아요!!

myNum = scan()  # 숫자만 받아들여요!!
myNum

myData = scan(what = character()) # 문자를 받아들여요!
myData

# 만약 data frame에 데이터를 직접 입력하고 싶으면??
df = data.frame()
my_df = edit(df)
my_df


## 파일처리!!!!

## 파일에 있는 데이터를 가져오기 위해서 여러가지 
## 형태의 함수를 이용

## read.table() 함수를 이용해요!!

df <- read.table("data/student_midterm.txt",
                 sep = ",")
df

## file에 header가 포함되어 있는 경우
df <- read.table("data/student_midterm.txt",
                 sep = ",",
                 header = TRUE,
                 fileEncoding = "UTF-8")
df

## 파일탐색기를 이용해서 파일을 선택할 경우
df <- read.table(file.choose(),
                 sep = ",",
                 header = TRUE,
                 fileEncoding = "UTF-8")
df

## 만약 파일에 이상데이터가 있는 경우
df <- read.table(file.choose(),
                 sep = ",",
                 header = TRUE,
                 fileEncoding = "UTF-8",
                 na.strings = "-")
df

#################################################

# 데이터를 교환, 전달할때 사용하는 데이터 표준형식

# 1. CSV(comma seperated value) 방식
#    CSV파일을 이용해서 사용.
#    예)홍길동,20,서울,김길동,40,부산,신사임당,30,인천
#    장점 - 용량이 작아요 => 대용량의 데이터 전달 유리
#    단점 - 데이터의 구조화가 힘들어요!
# 2. XML(extended markup language) 방식
#    예)<name>홍길동</name><age>20</age><address>서울</address>
#    단점 - 데이터 전체의 크기가 너무크게 증가.
# 3. JSON(JavaScript Object Notation)
#    예){ name : "홍길동", age : 20, address : "서울" }

###############################################
# read.table()과 거의 유사
# header=TRUE default, sep="," default

df = read.csv(file.choose(),
              fileEncoding = "UTF-8")
df

###############################################

# Excel파일로 데이터 파일이 되어 있는 경우
# Excel file을 사용하려면 기본기능으로는 안되고
# 외부 package를 이용해야 해요!

install.packages("xlsx")
require(xlsx)

df = read.xlsx(file.choose(),
               sheetIndex = 1,
               encoding = "UTF-8")
df
class(df)

# file에 data frame을 저장하려면??
# write.table()

write.table(df,
            file="data/result.csv")

write.table(df,
            file="data/result.csv",
            row.names = FALSE, # index출력하지마!!
            quote = FALSE)  

###################################################

# R에서 JSON처리
# JSON데이터를 어디서 얻나요??
# 1. 우리가 DB설정, 간단한 Servlet을 이용해서 JSON을
#    받아올꺼예요!!
#
# 2. OPEN API(영화진흥위원회 OPEN API)
#
#
# 도서검색 프로그램을 이용해서 JSON을 이용해 보아요!
# - 데이터베이스 세팅
# - mysql을 이용해서 데이터베이스 설정(standalone)
# - 1. mysql압축을 풀어요
# - 2. bin폴더 안에 있는 mysqld를 실행(daemon)
#      => myslq server가 기동
# - 3. mysql에 console진입(새로운 도스창을 열어서)
#      => mysql -u root
# - 4. 새로운 사용자를 생성
#      => create user rdata identified by "rdata";
#      => create user rdata@localhost identified by "rdata";
# - 5. 데이터베이스 생성
#      => create database library;
# - 6. 데이터베이스 권한 설정
#      => grant all privileges on library.* to rdata;
#      => grant all privileges on library.* to rdata@localhost;
# - 7. flush privileges; 
# - 8. console에서 나가요!! => exit;
# - 9. 제공된 script를 이용하여 database 구축!!
#      mysql -u rdata -p library < _BookTableDump.sql

#################################################
 
# R에서 외부 Web Application 호출한 후 결과를 
# Data Frame으로 받아올꺼예요!!

# 결과로 얻은 JSON -> Data Frame으로 변환해야 해요!
# 외부 package를 이용

install.packages("jsonlite")
install.packages("curl")
require(curl)
require(jsonlite)   # json처리를 위해서 loading
require(stringr)    # 문자열 처리를 위해서 loading

url <- "http://localhost:8080/bookSearch/search?keyword="

request_url <- str_c(url,scan(what=character()))

request_url

df = fromJSON(request_url)

View(df)

for(i in 1:nrow(df)) {
  print(df$title[i])
}

.libPaths("C:/Users/student/Documents/R/win-library/3.6")

### 외부 API를 이용해서 JSON데이터를 획득한 후 
### 결과를 출력해보아요!!

### 영화진흥위원회 OPEN API를 이용해 보아요!!

# package설치는 했어요!! (jsonlite, curl)
# package loading (require,library)

url <- "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=629d53a9d31fcd5c14481b3797a7f22f&targetDt=20200310"

df = fromJSON(url)

View(df)

# df ===> list로 되어 있어요!!
df[[1]]
df[["boxOfficeResult"]]

for(i in 1:nrow(df$boxOfficeResult$dailyBoxOfficeList)) {
  print(df$boxOfficeResult$dailyBoxOfficeList$movieNm[i])
}
df$boxOfficeResult$dailyBoxOfficeList$movieNm


