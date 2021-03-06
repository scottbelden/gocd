##########################GO-LICENSE-START################################
# Copyright 2014 ThoughtWorks, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
##########################GO-LICENSE-END##################################

require File.join(File.dirname(__FILE__), "/../../../spec_helper")

describe 'admin/pipelines/pause_info.json.erb' do

  it "should create json with 'pause_info_and_control' partial" do
    pause_info = mock('some pause info')
    assigns[:pause_info] = pause_info
    pipeline = mock('pipeline')
    pipeline.should_receive(:name).and_return('mingle')
    assigns[:pipeline] = pipeline

    template.should_receive(:render_json).with(:partial => "shared/pause_info_and_control.html", :locals => {:scope => {:pause_info => pause_info, :pipeline_name => 'mingle'}}).and_return("\"pause_fragment\"")

    render 'admin/pipelines/pause_info.json.erb'

    response.body.should == <<EOF.strip
{
    "pause_info_and_controls": {"html" : "pause_fragment"}
}
EOF
  end
end


