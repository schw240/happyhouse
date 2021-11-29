import requests
from bs4 import BeautifulSoup
import re
import pymysql
from apscheduler.schedulers.blocking import BlockingScheduler  # 스케줄러


# 네이버 뉴스기사 100개 이외의 기사 삭제
def naver_news_remove(conn):
    cursor = conn.cursor()

    # sql = """select min(upload_date) from (
    #             select upload_date
    #             from NaverNews
    #             order by upload_date desc limit 9) a"""

    # cursor.execute(sql)
    # results = cursor.fetchall()

    sql = f"""delete from NaverNews
                where upload_date < (select min(upload_date) from (
                select upload_date
                from NaverNews
                order by upload_date desc limit 7) a)"""

    cursor.execute(sql)
    conn.commit()
    print('기사삭제완료')


def newsCrawl(conn):
    # 에이전트 요청 헤더로 네트워크에게 크롤링하려는 크롤러라는 정보를 알려줌
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}

    # print(soup)
    # 한페이지에 10개씩 뉴스 들어있음
    # while True:
    wantToSearch = "부동산"
    url = f'https://search.naver.com/search.naver?&where=news&query={wantToSearch}'

    data = requests.get(url, headers=headers)
    soup = BeautifulSoup(data.text, 'html.parser')

    lis = soup.select('#main_pack > section > div > div.group_news > ul li')

    for li in lis:
        news_tit = ''
        news_link = ''
        news_name = ''
        news_date = ''
        news_article = ''

        news_tit = li.select('div.news_wrap.api_ani_send > div > a')

        # print(news_tit)

        if len(news_tit) == 0:
            continue
        else:
            # 타이틀
            # print(news_tit[0].attrs['title'])

            # 해당 뉴스 링크
            # print(news_tit[0].attrs['href'])

            if len(li.select("a.info")) == 2:

                try:
                    # 네이버 뉴스로 보는 링크
                    url2 = li.select("a.info")[1].attrs['href']
                    # print(url2)

                    data2 = requests.get(url2, headers=headers)
                    soup2 = BeautifulSoup(data2.text, 'html.parser')

                    # 신문사
                    news_name = soup2.select_one(
                        'div.press_logo > a > img')
                    # print(news_name)

                    # 날짜
                    news_date = soup2.find('span', {'class': 't11'})
                    # print(news_date)

                    news_article = soup2.select_one(
                        '#articleBodyContents').text

                    if news_article is None:
                        continue
                    else:
                        news_article = soup2.select_one(
                            '#articleBodyContents').text
                        news_article = news_article.replace(
                            '// flash 오류를 우회하기 위한 함수 추가', '')
                        news_article = news_article.replace(
                            'function _flash_removeCallback() {}', '')
                        news_article = news_article.replace('동영상 뉴스', '')
                        news_article = news_article.replace(
                            '무단전재 및 재배포 금지', '')
                        news_article = news_article.replace('\'', '')
                        news_article = news_article.strip()
                        pretty_news_article = re.sub(
                            '[가-힣]{2,3} *기자|▶.*|[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+|\[[가-힣]{2,5} *|\[[가-힣].*\]]', '', news_article)  # idk

                    title = news_tit[0].attrs['title']
                    # print(title)
                    link = news_tit[0].attrs['href']
                    # print(link)
                    company = news_name.attrs['title']
                    # print(company)
                    upload_date = news_date.text
                    # print(upload_date)
                    content = pretty_news_article
                    # print(content)

                    naver_news_info(conn, title, link, company,
                                    upload_date, content)
                except:
                    print("뉴스 중복 입력 X")


def naver_news_info(conn, title, link, company, upload_date, content):
    cursor = conn.cursor()

    sql = "INSERT INTO NaverNews(title, link, company, upload_date, content) values(%s,%s,%s,%s,%s);"
    cursor.execute(sql, (title, link, company, upload_date, content))
    conn.commit()
    print("기사 한번 입력 완료")


def db_connect():
    conn = pymysql.connect(host="localhost", user="ssafy",
                           password="ssafy", db="happyhouse", charset="utf8")

    return conn


# conn = db_connect()
# newsCrawl(conn)


# conn.close()

sched = BlockingScheduler()


# @sched.scheduled_job('interval', seconds=5, id='test_1')
def day_after():
    conn = db_connect()

    newsCrawl(conn)
    naver_news_remove(conn)

    conn.close()


sched.add_job(day_after, 'interval', seconds=30)
# sched.add_job(day_after, 'cron', hour=12)

print("스케줄러 실행?")
sched.start()
print("스케줄러 실행 완료")
