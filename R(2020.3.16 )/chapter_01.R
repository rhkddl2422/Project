# R의 주석은 #을 이용해요!!
# 여러줄 주석을 설정할때는 ctrl + shift + c
# ";"는 한 라인에 하나의 statement만 존재할 경우
# 생략이 가능해요!!
# 작성된 코드를 실행하려면 ctrl + enter
# case-sensitive(대소문자를 구분해요!)
# camel-case notation
# 변수 선언과 사칙연산
# weak type의 언어 => 변수선언시 type을 명시하지 않아요!!
# "=", "->"를 이용해서 assignment를 수행

# data type(자료형) , data structure(자료구조)
# vector : 1차원, 같은 데이터 타입만 사용할 수 잇어요
# vector중에 원소가 1개만 있는 vector => scalar
# R은 index가 0이 아니라 1부터 시작
# 변수에 값을 출력하려면
#  - 해당 변수를 그대로 실행
#  - print()를 이용해서 출력
#  - 만약 여러개의 값을 출력하려면 cat()을 이용
#  - file에 출력하려면 cat을 이용하여 file option을 이용
#  - 만약 file출력에서 파일안에 내용을 추가하려면
#  append=TRUE를 이용

myVar <- 100
result = myVar + 200

cat("결과값은 : ",result,myVar,
    file="C:/R_Workspace/R_Lecture/test.txt",
    append=TRUE)

############################################

# 기본적인 연산자는 다른 언어와 상당히 유사

var1 <- 100
var2 <- 3

result <- var1 / var2

result   # 33.33333 (총 7개의 digit로 표현 - default)
options(digits = 5)
result   # 33.333 (5개의 digit로 표현)
# c와 java처러 format을 이용한 출력도 가능
sprintf("%.8f",result)
# 몫을 구하고 나머지를 구해보아요!!

result = var1 %/% var2  # 몫을 구할때 사용
result
result = var1 %% var2   # 나머지를 구할때
result

#############################################

# 비교연산 => 다른언어와 같습니다!!
var1 = 100
var2 = 200

var1 == var2   
var1 != var2

############################################

# 논리연산 => 약간 주의를 해야 해요!!

# &, && => 의미는 같아요! ( AND 연산 )
# |, || => 의미는 같아요! ( OR 연산 )
# 하나짜리와 두개짜리는 vector인지 scalar인지에 따라서 동작이 달라져요!
# combine 함수를 이용해서 vector를 생성 => c()
# &를 이용하면 백터화 연산을 수행(같은 위치끼리 연산)
c(TRUE,FALSE) & c(TRUE,TRUE)
c(TRUE,FALSE) & c(TRUE,TRUE,FALSE)
# &&를 이용하면 맨 처음에 있는 요소를 가지고 연산
c(TRUE,FALSE) && c(TRUE,TRUE)
c(TRUE,FALSE) && c(TRUE,TRUE,FALSE)

#################################################

# 기본적으로 사용하는 함수들이 많이 있어요!
abs(-3)   # 절대값을 구해요!! 3
sqrt(4)   # 제곱근을 구할 수 있어요!
factorial(4) # 팩토리얼


################################################

# R의 데이터 타입!! 
# 1. numeric(수치형) : 정수와 실수를 구분하지 않아요!
#    100, 100.3, 10L(정수) 나머지는 실수
# 2. character(문자열) : 하나의 문자, 문자열 둘 다 문자열로 간주되고 '', " " 혼용해서 사용이 가능
var1 = '홍길동'
# 3. logical(논리형) : TRUE(T), FALSE(F)
# 4. complex(복소수형) : 4-3i

# 특수데이터 타입
# 1. NULL (java의 null과 유사한 의미) - 존재하지 않는 객체를 지칭할때 사용
# 2. NA( Not Available ) - 일반적으로 결측치를 표현할때 사용(missing value)
# 3. NAN( Not A Number ) : 수치값이지만 숫자로 표현이 안되는 값
sqrt(-9)
# 4. Inf(Infinite) : 양의 무한대
3 / 0

###############################################

# R에서 제공하는 기본적인 함수 2개
# mode() : 데이터 타입을 알려주는 함수
# var1 <- 100
var1 <- TRUE
mode(var1)

# is 계열의 함수(여러가지 종류가 있어요!!)
var2 = 300
is.integer(var2)

##############################################

# 데이터타입의 우선순위
# character > complex > numeric > logical

# 기본적으로 사용되는 자료구조가 vector
myVar = c(10,20,30,40)
myVar

myVar = c(10,20,30,TRUE)
myVar

myVar = c(10,"홍길동",30,TRUE)
myVar

# as 계열의 함수를 이용한 casting(형변환)
myVar = "100"
as.numeric(myVar)

# 데이터 타입에 대한 이야기!!
###############################################

# R package

# R의 package는 처리할 Data + 기능(함수,알고리즘)

# R의 package시스템은 
# 1. base System
#    - base package
#    - recommended package
# 2. other package

# 간단하게 package를 하나 설치해 보아요!
install.packages("ggplot2")
# 어딘가에 설치가 되요!!
# package를 삭제하려면
remove.packages("ggplot2")

# 어디에 설치됬는지 확인해 보아요!!
.libPaths()
# 설치위치를 변경하려면
.libPaths("C:/myLib")

# 이렇게 package를 설치한 후 사용을 하기 위해서는
# 메모리에 loading을 해야 해요!!
library(ggplot2)
require(ggplot2)

myVar = c("남자","여자","여자","여자","여자","남자")

qplot(myVar)

help(mean)
# help()를 이용해도 좋으나 RDocumentation.org 사이트를 이용하는게 더 좋아보여요!!

####################################################

## 자료구조

## 자료형은 저장된 데이터의 타입을 지칭
## 자료구조는 데이터가 메모리에 어떤 방식으로 저장되어 있는가

## homogeneous(같은 종류의)
#  - 1. vector : 1차원 선형구조. 순서개념이 존재.
#                같은종류의 데이터 타입을 이용
#  - 2. matrix : 2차원 구조. 인덱스를 사용할 수 있어요
#                같은종류의 데이터 타입을 이용
#  - 3. array : 3차원 이상의 구조. 인덱스를 사용할 수 있어요. 같은종류의 데이터 타입을 이용
## heterogeneous(다른 종류의)
#  - 1. list : 1차원 선형구조. 순서개념이 존재
#              실제저장되는 구조는 map구조
#  - 2. data frame : 2차원 테이블 구조


##################################################

# vector : 1차원 선형자료구조. 순서의 개념이 있어요
#          index를 이용하여 vector를 사용할 있어요
#          index의 시작은 1.
#          []를 이용해서 각 요소를 access할 수 있어요
#          요소가 1개짜리 vector => scalar

myVar = c(100)   # myVar = 100

# vector를 만드는 방법
# 1. combine() 함수를 이용해요! => c()
#    vector를 만드는 가장 대표적인 방법
#    2개 이상의 vector를 하나의 vector로 만들때도
#    사용할 수 있어요!
myVar1 = c(10,20,30)
myVar2 = c(3.14,10,100)

myVar1
myVar2

result <- c(myVar1,myVar2)
result
# 2. ":"을 이용해서 만들 수 있어요!
#    수치형데이터에만 사용할 수 있고 단조증가,단조감소
#    형태의 vector를 생성
myVar = 1:10  # (start:end)
myVar  
myVar = 8.7:2    
# 3. 2번의 일반형
myVar = seq(1,10,2)
myVar = seq(from=10,to=3,by=-3)
myVar  
# 4. 반복적인 값을 이용해서 vector생성
#    rep()
myVar = rep(1:3, times=3)
myVar
myVar = rep(1:3, each=3)
myVar

# 많이 사용하는 함수중 하나가 vector안의 요소 개수를
# 알아내는 함수
myVar = c(10,20,30,40)
length(myVar)

# vector 요소의 사용(indexing 방식)
myVar = c(3.14,100,"Hello",TRUE,300)
myVar

##### 
myVar[1]   # 첫번째 요소를 access하기 위해서 사용
myVar[length(myVar)] # 마지막 요소를 access하기 위해
result = myVar[2:4]  # slicing
result
result = myVar[c(2,3,5)]   # fancy indexing
result
myVar[-1]
myVar[-(3:4)]
myVar[-c(1,4,5)]

### vector 데이터에 이름을 붙여보아요!!
myVar = c(10,20,30,40,50)
myVar

names(myVar)
names(myVar) = c("a","b","c","d","e")

names(myVar)
myVar

myVar[1]
myVar["a"]

############################################

# vector 연산

myVar1 = 1:3    # 1 2 3
myVar2 = 4:6    # 4 5 6

result = myVar1 + myVar2
result

myVar3 = 1:6    # 1 2 3 4 5 6

result = myVar1 + myVar3   # (recycle rule)
#        1 2 3 1 2 3    1 2 3 4 5 6
result


# vector에 대한 집합연산(합집합, 교집합, 차집합)
var1 = 1:5   # 1 2 3 4 5
var2 = 3:7   # 3 4 5 6 7

union(var1,var2)
intersect(var1,var2)
setdiff(var1,var2)
