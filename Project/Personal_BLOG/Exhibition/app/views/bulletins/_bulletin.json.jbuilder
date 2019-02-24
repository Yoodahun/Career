json.extract! bulletin, :id, :title, :area, :created_at, :updated_at
json.url bulletin_url(bulletin, format: :json)
