library(rvest)
#website <- html("http://minoan.deaditerranean.com/linear-b-transliterations/knossos/kn-a/kn-ag/")
website <- html("http://minoan.deaditerranean.com/linear-b-transliterations/knossos/kn-o/kn-od/")
letters = letters[seq( from = 1, to = 10 )]


# gets title of the tablets
website %>%
  html_nodes("h2") %>%
  html_text()

# gets text from the tablets
raw_data = website %>%
  html_nodes("p") %>%
  html_text()

tablets = vector <- c()

for (data in raw_data) {
  if(grepl("-[a-z]", data)) {
    tablets <- c(tablets, data)
  }
}
tablets


