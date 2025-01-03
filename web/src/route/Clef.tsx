import {useParams} from "react-router";
import {ClefNotes} from "../clef/ClefNotes";
import {JSX} from "react";

const allPageTypes = ['notes'] as const
type PageType = (typeof allPageTypes)[number]
const isPageType = (value: string): value is PageType => {
  return allPageTypes.includes(value as PageType)
}
const pages: Record<PageType, JSX.Element> = {
  notes: <ClefNotes/>
}

export const Clef = () => {
  const {type} = useParams()
  if (!type) {
    throw new Error('No page type provided')
  }
  if (!isPageType(type)) {
    throw new Error(`No page type found for ${type}`)
  }
  return pages[type]
}