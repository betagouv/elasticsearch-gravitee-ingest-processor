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

import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.RandomDocumentPicks;
import org.elasticsearch.test.ESTestCase;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class GdprProcessorTests extends ESTestCase {

    public void testThatProcessorWorks() throws Exception {
        Map<String, Object> document = new HashMap<>();
        document.put("uri", "/api-particulier/impots/impots/svair?numeroFiscal=123&referenceAvis=456");
        IngestDocument ingestDocument = RandomDocumentPicks.randomIngestDocument(random(), document);

        GdprProcessor processor = new GdprProcessor(randomAlphaOfLength(10), "description");
        Map<String, Object> data = processor.execute(ingestDocument).getSourceAndMetadata();

        assertThat(data, hasKey("uri"));
        assertThat(data.get("uri"), is("/api-particulier/impots/impots/svair"));
    }
}
