/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.server.service;

import com.thoughtworks.go.config.materials.git.GitMaterialConfig;
import com.thoughtworks.go.config.materials.tfs.TfsMaterialConfig;
import com.thoughtworks.go.domain.materials.TestingMaterialConfig;
import com.thoughtworks.go.domain.materials.dependency.DependencyMaterialInstance;
import com.thoughtworks.go.domain.materials.git.GitMaterialInstance;
import com.thoughtworks.go.domain.materials.mercurial.HgMaterialInstance;
import com.thoughtworks.go.domain.materials.packagematerial.PackageMaterialInstance;
import com.thoughtworks.go.domain.materials.perforce.P4MaterialInstance;
import com.thoughtworks.go.domain.materials.svn.SvnMaterialInstance;
import com.thoughtworks.go.domain.materials.tfs.TfsMaterialInstance;
import com.thoughtworks.go.helper.MaterialConfigsMother;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MaterialConfigConverterTest {
    @Test
    public void shouldFindTheMaterialInstanceTypeGivenAMaterialConfig() throws Exception {
        MaterialConfigConverter converter = new MaterialConfigConverter();

        assertEquals(SvnMaterialInstance.class, converter.getInstanceType(MaterialConfigsMother.svnMaterialConfig()));
        assertEquals(GitMaterialInstance.class, converter.getInstanceType(new GitMaterialConfig("abc")));
        assertEquals(HgMaterialInstance.class, converter.getInstanceType(MaterialConfigsMother.hgMaterialConfig()));
        assertEquals(P4MaterialInstance.class, converter.getInstanceType(MaterialConfigsMother.p4MaterialConfig()));
        assertEquals(TfsMaterialInstance.class, converter.getInstanceType(new TfsMaterialConfig(null)));
        assertEquals(DependencyMaterialInstance.class, converter.getInstanceType(MaterialConfigsMother.dependencyMaterialConfig()));
        assertEquals(PackageMaterialInstance.class, converter.getInstanceType(MaterialConfigsMother.packageMaterialConfig()));
    }

    @Test
    public void shouldThrowIfYouTryToFindTheInstanceTypeOfSomeRandomConfigType() throws Exception {
        MaterialConfigConverter converter = new MaterialConfigConverter();

        try {
            converter.getInstanceType(new TestingMaterialConfig());
            fail("Should have thrown up");
        } catch (Exception e) {
            assertEquals("Unexpected type: TestingMaterialConfig", e.getMessage());
        }
    }
}
