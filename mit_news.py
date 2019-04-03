from bs4 import BeautifulSoup
from urllib.request import urlopen
import datetime
import json

def mit_news_scraper():
    #MIT news on machine learning
    html = urlopen('https://news.mit.edu/topic/machine-learning')
    soup = BeautifulSoup(html , 'html.parser')
    article_cover_image = soup.findAll('div' , {'class' : 'article-cover-image'})
    articles_partial_urls = [cover.find('a')['href'] for cover in article_cover_image]
    articles_urls = [('https://news.mit.edu' + partial_url) for partial_url in articles_partial_urls]
    valid_articles_with_correct_tags = []
    current_date = datetime.datetime.today().strftime('%Y-%m-%d')

    for article in articles_urls:
        html = urlopen(article)
        soup = BeautifulSoup(html , 'html.parser')
        tags = soup.find('span' , {'class' : 'tags'}).findAll('a')
        for tag in tags:
            if(tag.get_text() == 'Machine learning'):
                valid_articles_with_correct_tags.append(article)

    result = {}
    urls = []
    for article in valid_articles_with_correct_tags:
        html = urlopen(article)
        soup = BeautifulSoup(html , 'html.parser')
        if(soup.find('span' , {'itemprop' : 'datePublished'})['content'] == current_date):
            urls.append(article)

    if len(urls) == 0:
        return "null"
    else:
        result['urls'] = urls
        return json.dumps(result)

if __name__ == '__main__':
    print(mit_news_scraper())