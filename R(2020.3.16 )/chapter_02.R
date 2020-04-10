## 3/9 R시작
## 데이터 분석
## R에서 EDA(탐색적 데이터 분석)
## - 주어진 데이터 안에서 알고자하는 데이터를 추출
## - 데이터 내에 숨겨져 있는 특정한 사실을 유추해는작업
## 통계적 가설 검정
## 머신러닝 -> 딥러닝으로 진행되는게 일반적인 수순.

## R 설치, RStudio 설치
## R Operator(연산자)
## R Data Type
## R Package
## R Data Structure
## - 6개의 자료구조가 존재해요!
## - 같은 데이터 타입이 들어가는 자료구조
##   - 1차원 : vector
##   - 2차원 : matrix
##   - 3차원 이상 : Array
## - 다른 데이터 타입도 사용가능한 자료구조
##   - 1차원 : list
##   - 2차원 : data frame

## vector에 대해서 알아보아요!!
## vector 생성방법, 사용방법, 집합연산

##################################################

# matrix : 2차원 구조(행과 열로 구성)
#          같은 데이터 타입만 저장이 가능

var1 <- matrix(c(1:5))  # 5행 1열짜리 2차원 matrix
var1

var1 <- matrix(c(1:10), nrow=2 )  # 2행 5열
var1

var1 <- matrix(c(1:11), nrow=2 )  # 2행 6열(recycle rule)
var1

var1 <- matrix(c(1:10), 
               nrow=2,
               byrow = TRUE)  # 2행 5열, 행방향으로 데이터를 채워요!!
var1
############################################

## matrix()를 이용해서 만들 수도 있구요!!
## 다른 함수를 이용해서 만들 수도 있어요!
## cbind(), rbind()를 이용해서 만들 수도 있어요!

var1 = 1:4
var2 = 5:8

mat1 = rbind(var1,var2)
mat1

mat1 = cbind(var1,var2)
mat1

##############################################

var1 <- matrix(c(1:10), 
               nrow=2,
               byrow = TRUE)  
var1

# 4라는 값을 이용하려면
var1[1,4]    # 일반적인 2차원 indexing방법과 동일

var1[,4]  # 모든행에 대해서 4열을 가져와!! (결과는 vector로 return)

var1[1,]

length(var1)  # 전체 요소(원소)의 개수
nrow(var1)    # 행의 개수
ncol(var1)    # 열의 개수

###################################################

# 3차원 array를 만들어 보아요!!
var1 <- array(c(1:24),
              dim=c(3,2,4))
var1
###################################################

# R factor
# factor는 범주형
# 방의 크기 (대, 중, 소) => level
# 명목형과 순서형이 있어요!
# level에 순서개념이 없으면 명목형(좌파, 우파)
# level에 순서개념이 있으면 순서형(대, 중, 소)

# factor를 생성할 때 vector를 이용해서 만들어요!

var1 = c("A","B","AB","O","A","AB")
var1

var1_factor <- factor(var1)
var1_factor

levels(var1_factor)  # factor의 level만 따로 출력

# 일반적으로 factor를 생성하는 방법

var1 = c("A","B","AB","O","A","AB")
var1_factor <- factor(var1,
                      levels = c("A","B","O","AB"),
                      ordered = TRUE)
var1_factor

################################################

# list
# 1차원 선형구조
# 여러형태의 자료형이 같이 저장될 수 있어요!
# 각 index 위치에 값이 저장될 때
# map형태로 저장되요!(key와 value의 쌍의 형태로 저장)

var1_scalar = 100

var2_vector = c(10,20,30)

var3_matrix = matrix(c(1:6), nrow = 3)

myList = list(var1_scalar,var2_vector,var3_matrix)

myList[[2]][1]

## 전형적인 방식으로 리스트를 만들어 보아요!!

myList <- list(name=c("홍길동","김길동"),
               age=c(20,30),
               gender=c("남자","여자"))
myList
myList$age   # key값을 이용해서 list 사용용
myList[[2]]  # 순번을 이용해서 access할 수 있어요!
myList[["age"]]

#################################################
# 기본적인 자료구조에 대한 사용법
# 

# data frame
# R에서 가장 많이 쓰이고 가장 중요한 자료구조
# 행과 열로 구성된 2차원 형태의 테이블
# 데이터베이스의 table과 같은 구조라고 생각하면 되요!
# 컬럼단위로 서로 다른 타입의 데이터 저장이 가능

df = data.frame(NO=c(1,2,3),
                Name=c("홍길동","이순신","강감찬"),
                Age=c(20,30,40))
df
View(df)   # View를 이용해서 data frame을 확인

# 내가 원하는 column만 보려면??
df$Name   # 문자열을 기본적으로 factor로 들어와요!

df = data.frame(NO=c(1,2,3),
                Name=c("홍길동","이순신","강감찬"),
                Age=c(20,30,40),
                stringsAsFactors=FALSE)
df
df$Name

# data frame을 살펴봤는데.. data frame중 일부를 
# 추출해서 또 다른 data frame을 생성할 수 있어요!!

df <- data.frame(x=c(1:5),
                 y=seq(2,10,2),
                 z=c("a","b","c","d","e"),
                 stringsAsFactors = FALSE)
df

# x값이 3이상인 행만 추출할꺼예요!
subset1 <- subset(df,x>=3)
subset1

# x값이 3이상이고 y값이 8 이하인 행만 추출하세요!
subset(df,x>=3 & y<=8)
#################################################

# 연습문제

# 1. 4,5,7,8,10,3의 숫자를 이용해서 숫자벡터 x를
#    생성하세요!!
x <- c(4,5,7,8,10,3)
print(x)

# 2. 다음 연산을 수행한 결과는?
x1 <- c(3,5,7,9)
x2 <- c(3,3,3)

x1+x2     # 6 8 10 12   
          # vector화 연산이 어떻게 수행되는가?
          # recycle rule이 적용되요!

# 3. Data Frame과 subset을 이용해서 다음의 결과를
#    만들어 보아요!!
Age <- c(22,25,18,20)
Name <- c("홍길동","최길동","박길동","김길동")
Gender <- c("M","F","M","F")

# 위의 3개의 vector를 이용하여 Data Frame을 생성하고
# subset을 이용해서 다음의 결과를 출력

## AGE   Name   Gender
## 22    홍길동 M
## 18    박길동 M

df <- data.frame(AGE=Age,
                 Name=Name,
                 Gender=Gender,
                 stringsAsFactors = FALSE)
df
subset(df,Gender != "F")

# 4. 다음의 R코드를 실행시킨 결과는 얼마일까요?
x <- c(2,4,6,8)
y <- c(TRUE,FALSE,TRUE,FALSE)

x[1]   # indexing
x[1:3] # slicing
x[y]   # boolean indexing
x[c(1,2,4)]   # fancy indexing

sum(x[y])  # sum(), mean(), max(), min(), ...  기본수학함수

# 5. 아래의 계산결과는?
#    
x <- c(1,2,3,4)
(x+2)[(!is.na(x)) & x>2] -> k
k

# 6. 결측치(missing value) => NA
x <- c(10,20,30,NA,40,50,60,NA,NA,100)
# 이 vector안에 결측치가 몇개 있나요?
is.na(x)
sum(is.na(x))
