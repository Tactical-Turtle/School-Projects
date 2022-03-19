# Post account data
curl -H "Content-Type: application/json" -X POST -d '{"balance": 100, "name":"checking"}' "http://localhost:3000/accounts" -i

# Update account data
curl -H 'Content-Type: application/json' -X PUT -d '{"balance": 200, "name":"savings"}' "http://localhost:3000/accounts/0" -i

# Get account data
curl "http://localhost:3000/accounts" -i

# Delete account data
curl -X DELETE "http://localhost:3000/accounts/0" -i