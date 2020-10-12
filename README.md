# Elasticsearch Gravitee GDPR Ingest Processor

This ES plugin is used with Gravitee in order to remove any personal information from the data received at ingest time.

This removes the query params from the stored URLs and URIs, as they contain sensitive information in the case of API Particulier.

## Usage

```
PUT _ingest/pipeline/gdpr-pipeline
{
  "description": "A pipeline to comply to GDPR",
  "processors": [
    {
      "gdpr" : {
      }
    }
  ]
}

PUT /my-index/my-type/1?pipeline=gdpr-pipeline
{
  "uri" : "/api-particulier/impots/impots/svair?numeroFiscal=123&referenceAvis=456"
}

GET /my-index/my-type/1
{
  "uri": "/api-particulier/impots/impots/svair"
}
```

## Setup

In order to install this plugin, you need to create a zip distribution first by running

```bash
gradle clean check
```

This will produce a zip file in `build/distributions`.

After building the zip file, you can install it like this

```bash
bin/elasticsearch-plugin install file:///path/to/ingest-gdpr/build/distribution/ingest-gdpr-0.0.1-SNAPSHOT.zip
```

# Publish

In order to create a Github release with the built files, you can run

```bash
gradle
```
