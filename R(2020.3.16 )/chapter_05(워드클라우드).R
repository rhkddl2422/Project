
## 데이터분석을 이용하려면
## 웹 데이터 분석이 가장 많지 않을까 생각되요!
## 의사결정에 반영하는 방식으로 이용. => 마케팅목적으로 많이 이용
## 추천시스템

## Graph에 대해서 알아보아요!
## 2차원 그래프를 그리는 방법에 대해서 알아보아요!
## ggplot2라는 package를 이용

## 1. 산점도(scatter plot)
## 2차원 평면(x축, y축)에 점으로 표현한 그래프

## ggplot2 package의 그래프 그리는 방식
## - 1. 배경설정(x축과 y축 설정)
## - 2. 실제 그래프를 추가(선, 막대, 점)
## - 3. 축범위, 색과 같은 설정을 추가

install.packages("ggplot2")  # R에서 가장 많이 사용하는 plot package

library(ggplot2)

mpg

df <- as.data.frame(mpg)  # table형식의 데이터를 data frame으로 변환
df

## 1. scatter를 그려볼꺼예요!
##    배기량과 고속도로 연비의 관계를 알기위해서 산점도를 
##    그려보아요!

## x축, y축 설정!!
ggplot(data=df,
       aes(x=displ, y=hwy))

## 그래프를 정해줘요!
ggplot(data=df,
       aes(x=displ, y=hwy)) + 
  geom_point()    # 산점도를 그리는 함수

## 설정을 넣을 수 있어요!
ggplot(data=df,
       aes(x=displ, y=hwy)) + 
  geom_point() +
  xlim(3,6) +
  ylim(20,40)

##############################################

## 막대그래프를 그려보아요!
## 일반적으로 두 집단간의 차이를 볼때 
## 우리가 가지고 있는 자동차 데이터를 이용해서
## 구동방식별 고속도로 연비차이를 비교해 보아요!

## 전륜구동, 후륜구동, 4륜구동
View(df)

## 모든 차량 데이터를 구동방식별로 grouping
result <- df %>%
          group_by(drv) %>%
          summarise(mean_hwy=mean(hwy))    
result

## 막대그래프를 이용해서 집단간의 차이를 비교

ggplot(data=result,
       aes(x=drv, y=mean_hwy)) +
  geom_col()   # 막대그래프

## boxplot을 알아보아요!
## 구동방식에 따른 고속도로 연비를 boxplot으로
## 그려보고 어떤 의미를 내포하고 있는지를 알아보아요!
ggplot(data=df,
       aes(x=drv, y=hwy)) +
  geom_boxplot()


data <- c(1,2,3,4,5,6 ,7, 8,9,10,11,12,50)

mean(data)


#################################################

## 문자열처리(워드 클라우드 작성)
## 한글 형태소 분석
## 형태소(뜻을가진 가장 작은 단위)

## 영화댓글사이트에서 특정 영화에 대한 댓글을
## 모아서 형태소를 분석한 후 해당 내용을 이용해서
## 워드 클라우드를 작성

## 한글 형태소를 분석하기 위해서는 특수한 package가
## 필요 => KoNLP(Korean Natural Language Process)

## package를 설치해요!!
install.packages("KoNLP")
## 현재 CRAN에서 설치가 안되고 있어요!!
## 폴더를 복사하는 형식으로 사용할 꺼예요!
## 2개의 폴더를 library폴더에 복사
.libPaths()
library(KoNLP)
#install.packages("")

# 사용할 수 있는 한글 사전은 3가지 종류
# 시스템, 세종, NIADIC
useNIADic()   

tmp <- "이것은 소리없는 아우성"

extractNoun(tmp)

library(stringr)   # package를 loading

txt <- readLines("data/hiphop.txt",
                 encoding = "UTF-8")
txt

words <- extractNoun(txt)  # list로 만들어져요

result <- unlist(words)    # list를 vector로 변환

result

# 빈도를 구해야 해요!!
# table() <- 빈도수를 구하는 함수

wordcount <- table(result)

wordcount

wordcount_df <- as.data.frame(wordcount,
                              stringsAsFactors = F)

wordcount_df

word_df <- wordcount_df %>%
           filter(nchar(result) >= 2) %>%
           arrange(desc(Freq)) %>%
           head(20)

word_df

## 워드 클라우드 작성 

install.packages("wordcloud")
library(wordcloud)

# 단어 색상 목록 설정
pal <- brewer.pal(8,"Dark2")  # 8개의 색상을 사용

# wordcloud는 생성할 때 마다 다르게 생성
# 만약 같은 형태의 wordcloud를 생성하고 싶으면
# random에 대한 seed값을 설정

set.seed(1)

wordcloud(words=word_df$result,   # 사용할 단어
          freq=word_df$Freq,      # 빈도수
          min.freq = 2,           # 최소 빈도수
          max.words = 200,        # 최대 단어수   
          random.order = F, # 고빈도단어중앙위치 
          rot.per = .1,
          colors=pal) 

######################################################

# 1. 데이터 분석 => R
#    데이터 타입, 데이터 구조(자료구조)
# 2. 데이터 수집 => 파일로부터 데이터를 받아와요!
#                   웹프로그램으로부터 JSON
#                   Open API를 이용해서 JSON
#                   scraping & crawling(selector,xpath)
# 3. 데이터 전처리(NA,이상치 처리)
# 4. 데이터 분석 => dplyr을 이용한 data frame처리
#                   EDA(탐색적 데이터 분석)
#                   주어진 데이터안에서 요구되는
#                   데이터를 추출하는 방법을 연습
#                   공공데이터(일반적인 raw데이터를
#                   이용해서 의미있는 데이터 추출)
# 3가지 문제를 해결해보아요!

# 수행평가 문제 3개

# 1. mpg data set을 이용한 8개 문제 해결.(dplyr)
# 2. 웹 스크래핑(로튼토마토)
# 3. 네이버영화댓글 사이트에서 특정 영화에 대한
#    댓글을 수집하여 wordcloud를 생성

# 궁금한 사항 & 기타 질문들이 있으면 질문!!




