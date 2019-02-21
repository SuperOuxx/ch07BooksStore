Narrative:
In order to 为了高效管理书店
As a store 作为书店管理员
I want to 希望能够对图书进行增删改查操作

Scenario: 图书细节表单应当拥有全部字段

Given UserIsOnTheBooksPage
Then Field bookId Exists
Then Field bookTitle Exists
Then Field bookAuthor Exists
Then Field bookDescription Exists

Scenario: 用户应当可以创建一本新书

Given UserIsOnTheBooksPage
When 用户点击newBook按钮
When UserSetValuesToTheBookForm
When 用户点击saveBook按钮
Then BookIsStored

Scenario: 用户应当可以展示图书细节

Given UserIsOnTheBooksPage
When 用户选择一本书
Then 图书表单包含所有数据

Scenario: 用户应当可以更新图书细节

Given UserIsOnTheBooksPage
When 用户选择一本书
When UserSetValuesToTheBookForm
When 用户点击saveBook按钮
Then BookIsStored

Scenario: 用户应当可以删除图书

Given UserIsOnTheBooksPage
When 用户选择一本书
When 用户点击deleteBook按钮
Then 删除图书