/*
 * Copyright [2020] [Stanislas Bernard]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.elasticsearch.plugin.ingest.gdpr;

import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

import java.io.IOException;
import java.util.Map;

public class GdprProcessor extends AbstractProcessor {

    public static final String TYPE = "gdpr";
    public static final String URIField = "uri";


    public GdprProcessor(String tag) throws IOException {
        super(tag);
    }

    @Override
    public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
        String uri = ingestDocument.getFieldValue(URIField, String.class);
        ingestDocument.setFieldValue(URIField, stripOutQueryParams(uri));

        return ingestDocument;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static final class Factory implements Processor.Factory {

        @Override
        public GdprProcessor create(Map<String, Processor.Factory> factories, String tag, Map<String, Object> config) throws Exception {
            return new GdprProcessor(tag);
        }
    }

    private String stripOutQueryParams(String rawUrl) {
        return rawUrl.split("\\?")[0];
    }
}
