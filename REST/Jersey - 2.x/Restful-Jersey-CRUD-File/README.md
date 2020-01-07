<h1>DOCUMENT API CRUD FILE</h1><br>
<div>
  <span>WADL: http://localhost:8080/RestfulJerseyDowUpServer_war_exploded/rest/application.wadl</span><br>
</div><hr>
<table>
  <tr>
    <th></th>
    <th>Method</th>
    <th>Endpoint</th>
    <th>Accept</th>
    <th>(Key)Form-data</th>
  </tr>
  <tr>
    <th>Upload</th>
    <td>POST</td>
    <td>/files/upload</td>
    <td>application/json</td>
    <td>uploadFile</td>
  </tr>
  <tr>
    <th>Download</th>
    <td>GET</td>
    <td>/files/{fileName}</td>
    <td>application/json</td>
    <td></td>
  </tr>
  <tr>
    <th>SelectAll</th>
    <td>GET</td>
    <td>/files</td>
    <td>application/json</td>
    <td></td>
  </tr>
  <tr>
    <th>Update</th>
    <td>PUT</td>
    <td>/files/update/{fileName}</td>
    <td>application/json</td>
    <td>updateFile</td>
  </tr>
  <tr>
    <th>Delete</th>
    <td>DELETE</td>
    <td>/files/delete/{fileName}</td>
    <td>application/json</td>
    <td></td>
  </tr>
</table>
<hr>
<span>Note</span>: File example include<br><br>
<table>
  <tr>
    <th>uploaded</th>
    <th>may be download</th>
  </tr>
  <tr>
    <td>
      <ul>
        <li>FileText-1</li>
        <li>FileText-2</li>
        <li>FileText-3</li>
        <li>FileText-4</li>
      </ul>
    </td>
    <td>
      <ul>
        <li>FileText-3</li>
        <li>FileText-4</li>
      </ul>
    </td>
  </tr>
</table>

<hr>
<p>Content Root Path (Folder Download && Upload): out/artifacts/RestfulJerseyDowUpServer_war_exploded/files</p>
<span>Author: Nguyễn Hoàng Hải - STU</span>
