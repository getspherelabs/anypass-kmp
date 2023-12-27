package io.spherelabs.help.domain.mapper

import io.spherelabs.helpstore.AskedQuestion
import io.spherelabs.helpstore.FAQs as StoreFAQs
import io.spherelabs.model.FAQ
import io.spherelabs.model.FAQs as DomainFAQs

fun StoreFAQs.asDomain(): DomainFAQs {
  return this.map { data -> data.asDomain() }
}

fun AskedQuestion.asDomain(): FAQ {
  return FAQ(
      uuid,
      question,
      answer,
  )
}
