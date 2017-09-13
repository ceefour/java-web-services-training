# http://www.geonames.org/export/geonames-search.html
import sys
import requests

q = sys.argv[1]
r = requests.get('http://api.geonames.org/searchJSON',
    params={'q': q, 'username': 'ceefour'})
print('HTTP status code: %s' % r.status_code)
print('HTTP response headers: %s' % r.headers)
print('Response:')
print(r.text)