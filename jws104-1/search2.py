# http://www.geonames.org/export/geonames-search.html
import sys
import requests

q = sys.argv[1]
r = requests.get('http://api.geonames.org/searchJSON',
    params={'q': q, 'username': 'ceefour'})
data = r.json()
print('%s places found for "%s"' % (len(data['geonames']), q))
for place in data['geonames']:
    print('%s (%s,%s)' % (place['toponymName'], place['lat'], place['lng']))
